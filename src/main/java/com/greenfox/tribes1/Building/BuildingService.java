package com.greenfox.tribes1.Building;

import com.greenfox.tribes1.Exception.BuildingNotValidException;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Kingdom.KingdomRepository;
import com.greenfox.tribes1.Resources.KingdomResource;
import com.greenfox.tribes1.Resources.ResourceFactory;
import com.greenfox.tribes1.Resources.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService {

  private BuildingRepository buildingRepository;
  @Autowired
  private KingdomRepository kingdomRepository;


  @Autowired
  public BuildingService(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
  }

  public boolean isValidBuilding(Building building) {
    return building != null;
  }

  public Building saveBuilding(Building building) throws BuildingNotValidException {
    if (isValidBuilding(building)) {
      return buildingRepository.save(building);
    } else throw new BuildingNotValidException("Building is not valid");
  }

  public void setStarterBuildings(Kingdom kingdom) {
    List<Building> buildings = new ArrayList<>();
    Building mine = BuildingFactory.createBuilding(BuildingType.mine);
    Building farm = BuildingFactory.createBuilding(BuildingType.farm);
    mine.setKingdom(kingdom);
    farm.setKingdom(kingdom);
    buildings.add(mine);
    buildings.add(farm);
    kingdom.setBuildings(buildings);
  }
}
