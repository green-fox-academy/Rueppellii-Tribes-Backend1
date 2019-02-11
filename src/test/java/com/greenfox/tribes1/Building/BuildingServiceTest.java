package com.greenfox.tribes1.Building;

import com.greenfox.tribes1.Exception.BuildingNotValidException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BuildingServiceTest {
  
  private BuildingService buildingService;
  @Mock
  private BuildingRepository buildingRepository;
  
  private Building barrack = new Barracks();
  private Building mine;
  
  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    buildingService = new BuildingService(buildingRepository);
  }
  
  @Test
  public void validBuilding_ReturnsTrue() {
    assertTrue(buildingService.isValidBuilding(barrack));
  }
  
  @Test
  public void notValidBuilding_ReturnsFalse() {
    assertFalse(buildingService.isValidBuilding(mine));
  }
  
  @Test
  public void saveValidBuilding_ReturnsBuilding() throws BuildingNotValidException {
    when(buildingRepository.save(barrack)).thenReturn(barrack);
    assertEquals(buildingService.saveBuilding(barrack), barrack);
  }
  
  @Test(expected = BuildingNotValidException.class)
  public void saveNotValidBuilding_ThrowsException() throws BuildingNotValidException {
    buildingService.saveBuilding(mine);
  }
}