package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserDTO;
import com.greenfox.tribes1.Exception.UsernameTakenException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
public class ApplicationUserServiceTest {
  private String username = "John";
  private String password = "password";
  private String encoded_password = "encoded_password";
  private ApplicationUserService applicationUserService;

  @MockBean
  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Mock
  ApplicationUserRepository applicationUserRepository;

  private ApplicationUserDTO testUserDTO = ApplicationUserDTO.builder()
          .username(username)
          .password(encoder.encode(password))
          .build();
  private ApplicationUser testUser;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    applicationUserService = new ApplicationUserService(applicationUserRepository);
    testUser = applicationUserService.createUserFromDTO(testUserDTO);
  }

  @Test(expected = UsernameTakenException.class)
  public void registerNewUser_ThrowException_IfUserAlreadyExist() throws UsernameTakenException {
    Mockito.when(applicationUserRepository.findByUsername(testUserDTO.getUsername())).thenReturn(Optional.of(testUser));
    applicationUserService.registerNewUser(testUserDTO);
  }

  @Test
  public void registerNewUser_ReturnsUser_IfUserNotExist() throws UsernameTakenException {
   //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
   // String encoded_password = encoder.encode(testUser.getPassword());
    Mockito.when(applicationUserRepository.save(Mockito.any(ApplicationUser.class))).thenReturn(testUser);
   // assertEquals(encoder.encode(password),testUser.getPassword());
    Mockito.when(encoder.encode(testUser.getPassword())).t(encoded_password);
    assertEquals(testUser, applicationUserService.registerNewUser(testUserDTO));
  }

  @Test
  public void findByUsername() {
    Mockito.when(applicationUserRepository.findByUsername(username)).thenReturn(Optional.of(testUser));
    assertEquals(testUser, applicationUserService.findByUsername(username));
  }
}