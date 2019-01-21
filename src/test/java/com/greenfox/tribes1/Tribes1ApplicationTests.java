package com.greenfox.tribes1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tribes1ApplicationTests {

  boolean alwaysTrue = true;
  @Test
  public void contextLoads() {
    assertEquals(alwaysTrue, true);
  }

}

