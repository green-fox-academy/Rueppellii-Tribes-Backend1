package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.ApplicationUser.ApplicationUserRepository;
import com.greenfox.tribes1.ApplicationUser.ApplicationUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ApplicationUserServiceTest {

  @Mock
  ApplicationUserRepository applicationUserRepository;

  private String username = "John";
  private String password = "password";
  private String email = "john@john.com";

  ApplicationUser testUser = new ApplicationUser(username, password, email);

  ApplicationUserService applicationUserService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    applicationUserService = new ApplicationUserService(applicationUserRepository);
  }

  @Test
  public void saveUserIfValidReturnsNullIfUserAlreadyExist() {
    Mockito.when(applicationUserRepository.findByUsername(testUser.getUsername())).thenReturn(Optional.of(testUser));
    assertNull(applicationUserService.saveUserIfValid(testUser));
  }

  @Test
  public void saveUserIfValidReturnsNullIfUsernameMissing() {
    assertNull(applicationUserService.saveUserIfValid(new ApplicationUser("", password, email)));
  }

  @Test
  public void saveUserIfValidReturnsNullIfPasswordMissing() {
    assertNull(applicationUserService.saveUserIfValid(new ApplicationUser(username, "", email)));
  }

  @Test
  public void saveUserIfValidReturnsUserIfUsernameAndPasswordPresentAndUserStillNotExist() {
    ApplicationUser testUser = new ApplicationUser(username, password, email);
    assertEquals(testUser, applicationUserService.saveUserIfValid(testUser));
  }


  @Test
  public void findByUsername() {
    Mockito.when(applicationUserRepository.findByUsername(username)).thenReturn(Optional.of(testUser));
    assertEquals(testUser, applicationUserService.findByUsername(username));
  }
}