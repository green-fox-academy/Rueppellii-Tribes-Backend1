package com.greenfox.tribes1.Kingdom;
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
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class KingdomServiceTest {

  KingdomService kingdomService;
  @Mock
  KingdomRepository kingdomRepository;

  private Kingdom validKingdom = new Kingdom("Narnia");
  private Kingdom validKingdom2 = new Kingdom("Rueppellii");
  private Kingdom notValidKingdom = new Kingdom(null);
  private KingdomDTO kingdomDTO = new ModelMapper().map(validKingdom, KingdomDTO.class);
  private List<Kingdom> testList = new ArrayList<>();


  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    kingdomService = new KingdomService(kingdomRepository);
  }

  @Test
  public void saveKingdom_WithValidName_ReturnKingdomWithValidName() throws NotValidKingdomNameException {
    Mockito.when(kingdomRepository.save(validKingdom)).thenReturn(validKingdom);
    assertEquals(kingdomService.saveKingdom(validKingdom), validKingdom);
  }

  @Test(expected = NotValidKingdomNameException.class)
  public void saveKingdom_WithInvalidName_ReturnException() throws NotValidKingdomNameException {
    kingdomService.saveKingdom(notValidKingdom);
  }

  @Test
  public void createKingdomDTOFromKingdom_GivesCorrectFieldValues() {
    assertEquals(kingdomService.createKingdomDTOFromKingdom(validKingdom).getKingdomName(), kingdomDTO.getKingdomName());
    assertEquals(kingdomService.createKingdomDTOFromKingdom(validKingdom).getId(), kingdomDTO.getId());
    assertEquals(kingdomService.createKingdomDTOFromKingdom(validKingdom).getApplicationUserName(), kingdomDTO.getApplicationUserName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createKingdomDTOFromKingdom_GivesError_IfNoKingdomProvided() {
    kingdomService.createKingdomDTOFromKingdom(null);
  }

  @Test
  public void findAll_GivesCorrectListOfKingdoms() {
    testList.add(validKingdom);
    testList.add(validKingdom2);
    when(kingdomRepository.findAll()).thenReturn(Arrays.asList(validKingdom, validKingdom2));
    assertEquals(kingdomService.findAll(), testList);
  }
}