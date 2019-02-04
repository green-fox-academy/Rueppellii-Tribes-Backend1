package com.greenfox.tribes1.Kingdom;
<<<<<<< HEAD

=======
import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
>>>>>>> f63b9d5332d81d504af64692f4058122b6f460c8
import com.greenfox.tribes1.Exception.NotValidKingdomNameException;
import com.greenfox.tribes1.Kingdom.DTO.KingdomDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

<<<<<<< HEAD
import static org.junit.Assert.assertEquals;
=======
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
>>>>>>> f63b9d5332d81d504af64692f4058122b6f460c8

@RunWith(SpringRunner.class)
public class KingdomServiceTest {
  
  KingdomService kingdomService;
  @Mock
  KingdomRepository kingdomRepository;
<<<<<<< HEAD
  
  private Kingdom validKingdom = new Kingdom("Narnia");
  private Kingdom notValidKingdom = new Kingdom(null);
  
=======

  private Kingdom validKingdomNarnia = new Kingdom("Narnia");
  private Kingdom validKingdomRueppellii = new Kingdom("Rueppellii");
  private Kingdom notValidKingdom = new Kingdom(null);
  private KingdomDTO kingdomDTO = new ModelMapper().map(validKingdomNarnia, KingdomDTO.class);
  private List<Kingdom> testList = new ArrayList<>();
  private Long testUserId = 1L;
  private String testUserName = "testuser";
  private String testUserpasswordassword = "password";
  private String testUserEmail = "testuser@user.com";
  private ApplicationUser testApplicationUser = new ApplicationUser(testUserId, testUserName, testUserpasswordassword, testUserEmail, validKingdomNarnia );



>>>>>>> f63b9d5332d81d504af64692f4058122b6f460c8
  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    kingdomService = new KingdomService(kingdomRepository);
  }
  
  @Test
  public void saveKingdom_WithValidName_ReturnKingdomWithValidName() throws NotValidKingdomNameException {
    Mockito.when(kingdomRepository.save(validKingdomNarnia)).thenReturn(validKingdomNarnia);
    assertEquals(kingdomService.saveKingdom(validKingdomNarnia), validKingdomNarnia);
  }
  
  @Test(expected = NotValidKingdomNameException.class)
  public void saveKingdom_WithInvalidName_ReturnException() throws NotValidKingdomNameException {
    kingdomService.saveKingdom(notValidKingdom);
  }

  @Test
  public void createKingdomDTOFromKingdom_GivesCorrectFieldValues() {
    assertEquals(kingdomService.createKingdomDTOFromKingdom(validKingdomNarnia).getKingdomName(), kingdomDTO.getKingdomName());
    assertEquals(kingdomService.createKingdomDTOFromKingdom(validKingdomNarnia).getId(), kingdomDTO.getId());
    assertEquals(kingdomService.createKingdomDTOFromKingdom(validKingdomNarnia).getApplicationUserName(), kingdomDTO.getApplicationUserName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createKingdomDTOFromKingdom_GivesError_IfNoKingdomProvided() {
    kingdomService.createKingdomDTOFromKingdom(null);
  }

  @Test
  public void findAll_GivesCorrectListOfKingdoms() {
    testList.add(validKingdomNarnia);
    testList.add(validKingdomRueppellii);
    when(kingdomRepository.findAll()).thenReturn(Arrays.asList(validKingdomNarnia, validKingdomRueppellii));
    assertEquals(kingdomService.findAll(), testList);
  }

  @Test
  public void findKingdomByApplicationUserName_GivesCorrectKingdom() {
    when(kingdomRepository.findKingdomByApplicationUser_Username("testuser")).thenReturn(validKingdomNarnia);
    assertEquals(kingdomService.findKingdomByApplicationUserName("testuser"), validKingdomNarnia);
  }
}