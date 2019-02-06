package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.ApplicationUser.ApplicationUserRepository;
import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Building.BuildingFactory;
import com.greenfox.tribes1.Building.BuildingType;
import com.greenfox.tribes1.Kingdom.DTO.KingdomDTO;
import com.greenfox.tribes1.Security.Model.JwtTokenFactory;
import com.greenfox.tribes1.TestTokenProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class KingdomControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ApplicationUserRepository applicationUserRepository;

  @Autowired
  private JwtTokenFactory tokenFactory;

  @MockBean
  private KingdomService kingdomService;

  private String token;
  private TestTokenProvider testTokenProvider;

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  private Long id = 1l;
  private String username = "username";
  private String userEmail = "user@user.com";
  private String kingdomName = "kingdomName";
  private Kingdom testKingdom;
  private KingdomDTO testKingdomDTO;
  private ApplicationUser testApplicationUser;
  private Building mine;

  String kingdom = ("{\n"
          + "\"id\":1,\n"
          + "\"kingdomName\":\"kingdomName\",\n"
          + "\"applicationUserName\":\"username\"\n"
          + "}\n");

  String mineJson = "[\n" +
          "    {\n" +
          "        \"id\": null,\n" +
          "        \"level\": null,\n" +
          "        \"started_at\": null,\n" +
          "        \"finished_at\": null,\n" +
          "        \"kingdom\": null,\n" +
          "        \"hp\": null\n" +
          "    }\n" +
          "]";

  String empty = "[]";

  @Before
  public void init() {
    testTokenProvider = new TestTokenProvider(applicationUserRepository, tokenFactory);
    testKingdom = Kingdom.builder()
            .id(id)
            .name(kingdomName)
            .build();
    testApplicationUser = ApplicationUser.builder()
            .id(id)
            .username(username)
            .userEmail(userEmail)
            .kingdom(testKingdom)
            .build();
    testKingdom.setApplicationUser(testApplicationUser);
    testKingdomDTO = new ModelMapper().map(testKingdom, KingdomDTO.class);
    testKingdom.setBuildings(new ArrayList<>());
  }

  @Test
  public void getKingdom_throwsException_ifUserNotFound() throws Exception {
    token = testTokenProvider.createMockToken(username);
    when(kingdomService.findKingdomByApplicationUserName(username)).thenThrow(UsernameNotFoundException.class);
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .header("Authorization", token))
            .andExpect(status().isUnauthorized());
  }

  @Test
  public void getKingdom_returnsOK_ifUserFound() throws Exception {
    token = testTokenProvider.createMockToken(username);
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .header("Authorization", token))
            .andExpect(status().isOk());
  }

  //WORKING but CHECK NEEDED!!!!!!!!!!!!
  @Test(expected = NullPointerException.class)
  public void getKingdom_returnsError_ifTokenNotProvided() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
            // .header("fakeName", "noValues")
    )
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void getKingdom_returnsError_ifTokenNotValid() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .header("Authorization", "Bearer not.Valid.Token"))
            .andExpect(status().isUnauthorized());
  }

  @Test
  public void getKingdom_ReturnsKingdomDTO_StatusOK_HasCorrectMediaType_ServiceMethodsRunOnlyOnce() throws Exception {
    token = testTokenProvider.createMockToken(username);
    when(kingdomService.findKingdomByApplicationUserName(Mockito.any(String.class))).thenReturn(testKingdom);
    when(kingdomService.createKingdomDTOFromKingdom(testKingdom)).thenReturn(testKingdomDTO);

    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .header("Authorization", token)
    )
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(content().json(kingdom));
    verify(kingdomService, times(1)).findKingdomByApplicationUserName(Mockito.any(String.class));
    verify(kingdomService, times(1)).createKingdomDTOFromKingdom(testKingdom);
    verifyNoMoreInteractions(kingdomService);
  }

  @Test
  public void getKingdomBuilding_StatusOk_ReturnsMine() throws Exception {
    token = testTokenProvider.createMockToken(username);
    mine = BuildingFactory.createBuilding(BuildingType.mine);
    List<Building> buildingList = new ArrayList<>();
    buildingList.add(mine);
    testKingdom.setBuildings(buildingList);
    when(kingdomService.findKingdomByApplicationUserName(Mockito.any(String.class))).thenReturn(testKingdom);
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom/buildings")
                    .header("Authorization", token)
    )
            .andExpect(content().contentType(contentType))
            .andExpect(content().json(mineJson))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void getKingdomBuildings_returnsEmptyList() throws Exception {
    token = testTokenProvider.createMockToken(username);
    when(kingdomService.findKingdomByApplicationUserName(Mockito.any(String.class))).thenReturn(testKingdom);

    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom/buildings")
                    .header("Authorization", token)
    )
            .andDo(print())
            .andExpect(content().contentType(contentType))
            .andExpect(content().json(empty))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }
}

