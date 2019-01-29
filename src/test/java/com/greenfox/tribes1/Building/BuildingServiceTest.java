package com.greenfox.tribes1.Building;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BuildigServiceTest {

  BuildigService buildigService;
  @Mock
  BuildingRepository buildingRepository;

  Building barrack = new Barracks();
  Building mine;

  @Before
  public void init(){
    MockitoAnnotations.initMocks(this);
    buildigService = new BuildigService(buildingRepository);
  }
  @Test
  public void validBuilding_ReturnsTrue() {
    assertTrue(buildigService.isValidBuilding(barrack));
  }

  @Test
  public void notValidBuilding_ReturnsFalse(){
    assertFalse(buildigService.isValidBuilding(mine));
  }

  @Test
  public void saveValidBuilding_ReturnsBuilding() {
    when(buildingRepository.save(barrack)).thenReturn(barrack);
    assertEquals(buildigService.saveBuilding(barrack),barrack);

  }

  @Test
  public void saveNotValidBuilding_ReturnsNull(){
    assertEquals(buildigService.saveBuilding(mine),null);
  }
}