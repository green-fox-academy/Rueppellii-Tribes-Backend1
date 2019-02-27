package com.greenfox.tribes1.kingdomelement.Building;

import com.greenfox.tribes1.TimeService;
import com.greenfox.tribes1.Upgradable;
import com.greenfox.tribes1.kingdomelement.KingdomElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BuildingService extends KingdomElementService<Building> implements Upgradable<Building> {

  private BuildingRepository buildingRepository;

  @Autowired
  public BuildingService(JpaRepository<Building, Long> repository, TimeService timeService) {
    super(repository, timeService);
  }

  @Override
  protected Exception notFoundException() {
    return null;
  }

  @Override
  public void upgrade(Building building) {
    building.buildingUpgrade();
  }
}