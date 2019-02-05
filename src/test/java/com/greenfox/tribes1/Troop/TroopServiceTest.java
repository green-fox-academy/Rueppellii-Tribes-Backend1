package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.Exception.TroopNotValidException;
import com.greenfox.tribes1.Resources.ResourceService;
import com.greenfox.tribes1.Troop.Model.Troop;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TroopServiceTest {
  
  @Mock
  private TroopRepository troopRepository;
  @Mock
  private ResourceService resourceService;
  
  private TroopService troopService;
  private Troop testTroop = new TroopFactory(resourceService).makeTroop(TroopType.TestTroop);
  private Troop nullTroop = null;
  
  public TroopServiceTest() throws NotValidResourceException {
  }
  
  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    troopService = new TroopService(troopRepository);
  }
  
  @Test
  public void save_successful() throws TroopNotValidException {
    when(troopRepository.save(testTroop)).thenReturn(testTroop);
    assertEquals(troopService.save(testTroop), testTroop);
  }
  
  @Test(expected = TroopNotValidException.class)
  public void save_unsuccessful() throws TroopNotValidException {
    troopService.save(nullTroop);
  }
}
