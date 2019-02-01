package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Kingdom.DTO.KingdomDTO;
import com.greenfox.tribes1.Security.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.Collections;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(KingdomController.class)
public class KingdomControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private KingdomService kingdomService;
  @MockBean
  private UserDetailsServiceImpl userDetailsService;

  private  Long id = 1l;
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