package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserWithKingdomDTO;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Security.UserDetailsServiceImpl;
import com.greenfox.tribes1.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationUserController.class)
public class ApplicationUserControllerTest {
  private String username = "testUser";
  private String password = "password";
  private String kingdomName = "testKingdom";

  private Kingdom kingdom = new Kingdom()
          .builder()
          .name(kingdomName)
          .build();

  private ApplicationUser testUser = new ApplicationUser
          .ApplicationUserBuilder()
          .username(username)
          .password(password)
          .kingdom(kingdom)
          .build();

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ApplicationUserService applicationUserService;

  @MockBean
  UserDetailsServiceImpl userDetailsService;

  @Test
  public void register_unsuccessful() throws Exception {
    mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void register_successful() throws Exception {
    when(applicationUserService.createDTOwithKingdomfromUser(testUser)).thenReturn(new ApplicationUserWithKingdomDTO());

    mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content("{ \"username\" : \"testUser\", \"password\" : \"pass\", \"kingdomName\" : \"testKingdom\"}"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.username", is("testUser")))
            .andExpect(jsonPath("$.kingdomId", is(1)));
  }
}