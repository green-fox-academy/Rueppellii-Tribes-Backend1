/*
package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserDTO;
import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserWithKingdomDTO;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Security.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationUserController.class)
public class ApplicationUserControllerTest {

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
            .andExpect(status().isUnauthorized());
  }

  @Test
  public void register_withMissingField() throws Exception {
    String input = "{\n" +
            "    \"username\": \"\",\n" +
            "    \"password\": \"pass\",\n" +
            "    \"kingdomName\": \"testKingdom\"\n" +
            "}";

    String result = "{\n" +
            "    \"status\": \"error\",\n" +
            "    \"message\": \"Missing parameter(s): {username=must not be blank}\"\n" +
            "}";

    mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(input))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(result));
  }

  @Test
  public void register_successful() throws Exception {
    String username = "testUser";
    String password = "password";
    String kingdomName = "testKingdom";

    Kingdom kingdom = Kingdom.builder()
            .id(1L)
            .name(kingdomName)
            .build();

    ApplicationUser testUser = ApplicationUser.builder()
            .id(1L)
            .username(username)
            .password(password)
            .kingdom(kingdom)
            .build();

    ModelMapper modelMapper = new ModelMapper();

    ApplicationUserWithKingdomDTO testUserDTOWithKingdom = modelMapper.map(testUser, ApplicationUserWithKingdomDTO.class);

    String input = "{\n" +
            "    \"username\": \"testUser\",\n" +
            "    \"password\": \"pass\",\n" +
            "    \"kingdomName\": \"testKingdom\"\n" +
            "}";

    String result = "{\n" +
            "    \"id\": 1,\n" +
            "    \"username\": \"testUser\",\n" +
            "    \"kingdomId\": 1\n" +
            "}";

    when(applicationUserService.registerNewUser(any(ApplicationUserDTO.class))).thenReturn(testUser);
    when(applicationUserService.createDTOwithKingdomfromUser(testUser)).thenReturn(testUserDTOWithKingdom);

    mockMvc.perform(post("/register")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(input))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(result));

    verify(applicationUserService, times(1)).registerNewUser(any(ApplicationUserDTO.class));
    verify(applicationUserService, times(1)).createDTOwithKingdomfromUser(testUser);
    verifyNoMoreInteractions(applicationUserService);
  }
}*/
