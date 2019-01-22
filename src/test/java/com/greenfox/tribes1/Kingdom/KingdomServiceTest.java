package com.greenfox.tribes1.Kingdom;

import org.aspectj.weaver.ast.Not;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KingdomServiceTest {



    @Autowired
    KingdomService kingdomService;

    @MockBean
    KingdomRepository kingdomRepository;

    private Kingdom kingdom = new Kingdom("Narnia");
    private Kingdom kingdom2 = new Kingdom(null);

    @Test
    public void saveKingdomTest1(){
        assertEquals(kingdomService.saveKingdom(kingdom),kingdom);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void saveKingdomTest2(){
        kingdomService.saveKingdom(kingdom2);
    }
}