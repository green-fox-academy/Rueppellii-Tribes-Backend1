package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Kingdom.DTO.KingdomDTO;
import com.greenfox.tribes1.Security.UserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockBodyContent;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(KingdomController.class)
public class KingdomControllerTest {

  @Autowired
  private MockMvc mockMvc;

//  private KingdomController kingdomController;
//  @MockBean
//  private KingdomRepository kingdomRepository;
  @MockBean
  private KingdomService kingdomService;
  @MockBean
  private UserDetailsServiceImpl userDetailsService;

  private  Long id = 1l;
  private String username = "username";
  private String password = "password";
  private String userEmail = "user@user.com";

  private String kingdomName = "kingdomName";

//  @Before
//  public void init() {
//    MockitoAnnotations.initMocks(this);
//    kingdomController = new KingdomController(kingdomService);
//  }




  @Test
  public void kingdomDTO() throws Exception {
    ApplicationUser testApplicationUser = new ApplicationUser(username, password, userEmail);
    testApplicationUser.setId(1L);
    Kingdom testKingdom = new Kingdom(kingdomName);
    testKingdom.setId(1L);

    KingdomDTO testKingdomDTO = new KingdomDTO(id, kingdomName, username);

    testApplicationUser.setKingdom(testKingdom);
    testKingdom.setApplicationUser(testApplicationUser);
//    KingdomDTO testKingdomDTO = new ModelMapper().map(testKingdom, KingdomDTO.class);

    when(kingdomService.findByApplicationUser(testApplicationUser)).thenReturn(testKingdom);
    when(kingdomService.createKingdomDTOFromKingdom(testKingdom)).thenReturn(testKingdomDTO);

    String json = String.format("{\n"
            + "\"id\":1\n"
            + "\"username\":\"username\"\n"
            + "\"userEmail\":\"user@user.com\"\n"
            + "\"kingdom\":\"kingdomName\"\n"
            + "}\n");


    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdom")
                    .contentType(MediaType.APPLICATION_JSON)
//                    .header("Authorization", adminToken)
                    .content(json)
    )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
//            .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[0].username", is("username")));
  }

  @Test
  public void findAllKingdom() throws Exception {
    when(kingdomService.findAll()).thenReturn(Collections.emptyList());

    mockMvc.perform(
            MockMvcRequestBuilders.get("/kingdomlist")
    )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
  }
}