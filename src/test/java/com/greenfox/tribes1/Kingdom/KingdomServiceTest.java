package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUserService;
import com.greenfox.tribes1.Exception.NotValidKingdomNameException;
import org.aspectj.weaver.ast.Not;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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
  public void saveKingdomTest1() throws NotValidKingdomNameException {
    Mockito.when(kingdomRepository.save(validKingdom)).thenReturn(validKingdom);
    assertEquals(kingdomService.saveKingdom(validKingdom), validKingdom);
  }

  @Test(expected = NotValidKingdomNameException.class)
  public void saveKingdomTest2() throws NotValidKingdomNameException {
    kingdomService.saveKingdom(notValidKingdom);
  }
}