package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.Exception.TroopNotValidException;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Resources.KingdomResource;
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
  private ResourceService resourceService;
  private Kingdom kingdom;
  private TroopService troopService;
  private TroopFactory troopFactory;
  private Troop testTroop;
  private Troop nullTroop = null;
  KingdomResource food;
  KingdomResource gold;
  
  public TroopServiceTest() throws NotValidResourceException {
  }
  
  @Before
  public void init() throws NotValidResourceException {
/*    resourceService = new ResourceService(resourceRepository);
    Kingdom kingdom = new Kingdom();
    List<KingdomResource> resources = new ArrayList<>();
    food = new Food(20L);
    gold = new Gold(20L);//     resources.add(food);
    resources.add(food);
    resources.add(gold);
    kingdom.setResources(resources);
    Troop testTroop = new TestTroop();
    testTroop.setKingdom(kingdom);
    kingdom.setTroops(Arrays.asList(testTroop));*/
    MockitoAnnotations.initMocks(this);
    troopFactory = new TroopFactory(resourceService);
    //when(troopFactory.makeTroop(TroopType.swordsman)).thenReturn(new Swordsman());
    //when(troopFactory.makeTroop(TroopType.swordsman)).thenReturn(new Swordsman());
    testTroop = troopFactory.makeTroop(TroopType.swordsman);
    //testTroop.getKingdom().setResources(resources);
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