package com.greenfox.tribes1.Building;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BuildingServiceTest {

  BuildingService buildingService;
  @Mock
  BuildingRepository buildingRepository;

  Building barrack = new Barracks();
  Building mine;

  @Before
  public void init(){
    MockitoAnnotations.initMocks(this);
    buildingService = new BuildingService(buildingRepository);
  }
  @Test
  public void validBuilding_ReturnsTrue() {
    assertTrue(buildingService.isValidBuilding(barrack));
  }

  @Test
  public void notValidBuilding_ReturnsFalse(){
    assertFalse(buildingService.isValidBuilding(mine));
  }

  @Test
  public void saveValidBuilding_ReturnsBuilding() {
    when(buildingRepository.save(barrack)).thenReturn(barrack);
    assertEquals(buildingService.saveBuilding(barrack),barrack);

  }

  @Test
  public void saveNotValidBuilding_ReturnsNull(){
    assertEquals(buildingService.saveBuilding(mine),null);
  }
}