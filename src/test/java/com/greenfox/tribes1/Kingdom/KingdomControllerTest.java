package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.ApplicationUser.ApplicationUserRepository;
import com.greenfox.tribes1.ApplicationUser.ApplicationUserService;
import com.greenfox.tribes1.Kingdom.DTO.KingdomDTO;
import com.greenfox.tribes1.Security.Model.JwtTokenFactory;
import com.greenfox.tribes1.TestTokenProvider;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
  private TestTokenProvider testTokenProvider;

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  private Long id = 1l;
  private String username = "username";
  private String userEmail = "user@user.com";
  private String kingdomName = "kingdomName";
  private Kingdom testKingdom;
  private ApplicationUser testApplicationUser;


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

  }

  @Test
  public void getKingdom_throwsException_ifUserNotFound() throws Exception {
    token = testTokenProvider.createMockToken(username);
    when(kingdomService.findKingdomByApplicationUserName(username)).thenThrow(UsernameNotFoundException.class);
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .contentType(contentType)
                    .header("Authorization", token))
            .andExpect(status().isUnauthorized());
  }

  @Test
  public void getKingdom_returnsOK_ifUserFound() throws Exception {
    token = testTokenProvider.createMockToken(username);
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .contentType(contentType)
                    .header("Authorization", token))
            .andExpect(status().isOk());
  }

  @Test
  public void getKingdom_returnsError_ifTokenNotProvided() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .contentType(contentType)
                    .header("fakeName", "noValues"))
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void getKingdom_returnsError_ifTokenNotValid() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .contentType(contentType)
                    .header("Authorization", "Bearer not.Valid.Token"))
            .andExpect(status().isUnauthorized());
  }

  @Test
  public void getKingdom_ReturnsKingdomDTO_StatusOK_HasCorrectMediaType_ServiceMethodsRunOnlyOnce() throws Exception {
    token = testTokenProvider.createMockToken(username);
    
    when(kingdomService.findKingdomByApplicationUserName(Mockito.any(String.class))).thenReturn(testKingdom);
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
                    .contentType(contentType)
                    .header("Authorization", token)
                    .content(json)
    )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(contentType))
            .andExpect(content().json(result));
    verify(kingdomService, times(1)).findKingdomByApplicationUserName(Mockito.any(String.class));
    verify(kingdomService, times(1)).createKingdomDTOFromKingdom(testKingdom);
    verifyNoMoreInteractions(kingdomService);
  }

  @Test
  public void findAllKingdom_StatusOk_GivesEmptyList() throws Exception {
    when(kingdomService.findAll()).thenReturn(Collections.emptyList());

    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom/building")
    )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void getKingdomBuildings_returnsEmptyList() throws Exception {
    Kingdom kingdom1 = new Kingdom("Kingdom1");
    Kingdom kingdom2 = new Kingdom("Kingdom2");

    when(kingdomService.findAll()).thenReturn(Arrays.asList(kingdom1, kingdom2));

    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom/building")
    )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$[0].name", is("Kingdom1")));
  }
}

