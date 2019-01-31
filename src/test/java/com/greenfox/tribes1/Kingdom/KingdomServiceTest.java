package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.Exception.NotValidKingdomNameException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class KingdomServiceTest {
  
  KingdomService kingdomService;
  @Mock
  KingdomRepository kingdomRepository;
  
  private Kingdom validKingdom = new Kingdom("Narnia");
  private Kingdom notValidKingdom = new Kingdom(null);
  
  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    kingdomService = new KingdomService(kingdomRepository);
  }
  
  @Test
  public void saveKingdomWithValidNameReturnKingdomWithValidName() throws NotValidKingdomNameException {
    Mockito.when(kingdomRepository.save(validKingdom)).thenReturn(validKingdom);
    assertEquals(kingdomService.saveKingdom(validKingdom), validKingdom);
  }
  
  @Test(expected = NotValidKingdomNameException.class)
  public void saveKingdomWithInvalidNameReturnException() throws NotValidKingdomNameException {
    kingdomService.saveKingdom(notValidKingdom);
  }
}