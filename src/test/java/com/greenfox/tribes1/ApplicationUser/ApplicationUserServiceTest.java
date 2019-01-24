package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserDTO;
import com.greenfox.tribes1.Exception.UsernameTakenException;
import com.greenfox.tribes1.Kingdom.KingdomRepository;
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
  private String username = "John";
  private String password = "password";
  private ApplicationUserService applicationUserService;

  @Mock
  ApplicationUserRepository applicationUserRepository;

  @Mock
  KingdomRepository kingdomRepository;

  private ApplicationUserDTO testUserDTO = new ApplicationUserDTO(username, password);
  private ApplicationUser testUser;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    applicationUserService = new ApplicationUserService(applicationUserRepository, kingdomRepository);
    testUser = applicationUserService.createUserFromDTO(testUserDTO);
  }

  @Test(expected = UsernameTakenException.class)
  public void saveUserIfValid_ThrowException_IfUserAlreadyExist() throws UsernameTakenException {
    Mockito.when(applicationUserRepository.findByUsername(testUserDTO.getUsername())).thenReturn(Optional.of(testUser));
    applicationUserService.saveUserIfValid(testUserDTO);
  }

  @Test
  public void saveUserIfValid_ReturnsUser_IfUserNotExist() throws UsernameTakenException {
    Mockito.when(applicationUserRepository.save(Mockito.any(ApplicationUser.class))).thenReturn(testUser);
    assertEquals(testUser, applicationUserService.saveUserIfValid(testUserDTO));
  }

  @Test
  public void findByUsername() {
    Mockito.when(applicationUserRepository.findByUsername(username)).thenReturn(Optional.of(testUser));
    assertEquals(testUser, applicationUserService.findByUsername(username));
  }
}