package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.ApplicationUser.ApplicationUserRepository;
import com.greenfox.tribes1.ApplicationUser.ApplicationUserService;
import com.greenfox.tribes1.Security.Model.JwtTokenFactory;
import com.greenfox.tribes1.TestTokenProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
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

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


  @Before
  public void init() {
    TestTokenProvider testTokenProvider = new TestTokenProvider(applicationUserRepository, tokenFactory);
    token = testTokenProvider.createMockToken("username");
  }

  @Test
  public void getKingdom_throwsException_ifUserNotFound() throws Exception {
    when(kingdomService.findKingdomByApplicationUserName("username")).thenThrow(UsernameNotFoundException.class);
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .contentType(contentType)
                    .header("Authorization", token))
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void getKingdom_returnsOK_ifUserFound() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .contentType(contentType)
                    .header("Authorization", token))
            .andExpect(status().isOk());
  }

}

/*
  private Long id = 1l;
  private String username = "username";
  private String userEmail = "user@user.com";
  private String kingdomName = "kingdomName";

  @Test
  public void kingdomDTO_StatusOk_GivesCorrectValues_HasCorrectMediaType_ServiceMethodsRunOnlyOnce() throws Exception {
    Kingdom testKingdom = Kingdom.builder()
            .id(id)
            .name(kingdomName)
            .build();
    ApplicationUser testApplicationUser = ApplicationUser.builder()
            .id(id)
            .username(username)
            .userEmail(userEmail)
            .kingdom(testKingdom)
            .build();
    testKingdom.setApplicationUser(testApplicationUser);
    KingdomDTO testKingdomDTO = new ModelMapper().map(testKingdom, KingdomDTO.class);

    when(kingdomService.findKingdomByApplicationUser(Mockito.any(ApplicationUser.class))).thenReturn(testKingdom);
    when(kingdomService.createKingdomDTOFromKingdom(testKingdom)).thenReturn(testKingdomDTO);

    String json = ("{\n"
            + "\"id\":1,\n"
            + "\"username\":\"username\",\n"
            + "\"userEmail\":\"user@user.com\",\n"
            + "\"kingdom\":\"kingdomName\"\n"
            + "}\n");

    String result = ("{\n"
            + "\"id\":1,\n"
            + "\"kingdomName\":\"kingdomName\",\n"
            + "\"applicationUserName\":\"username\"\n"
            + "}\n");

    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
//                    .header("Authorization", adminToken)
                    .content(json)
    )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(result));
    verify(kingdomService, times(1)).findKingdomByApplicationUser(Mockito.any(ApplicationUser.class));
    verify(kingdomService, times(1)).createKingdomDTOFromKingdom(testKingdom);
    verifyNoMoreInteractions(kingdomService);
  }

  @Test
  public void findAllKingdom_StatusOk_GivesEmptyListWhenNoKingdom() throws Exception {
    when(kingdomService.findAll()).thenReturn(Collections.emptyList());

    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdomlist")
    )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void findAllKingdom_StatusOk_GivesCorrectKingdomList() throws Exception {
    Kingdom kingdom1 = new Kingdom("Kingdom1");
    Kingdom kingdom2 = new Kingdom("Kingdom2");

    when(kingdomService.findAll()).thenReturn(Arrays.asList(kingdom1, kingdom2));

    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdomlist")
    )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$[0].name", is("Kingdom1")));
  }
}
*/
