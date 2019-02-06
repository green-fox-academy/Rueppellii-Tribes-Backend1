package com.greenfox.tribes1.Building;

import com.greenfox.tribes1.Exception.BuildingNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService {

  private BuildingRepository buildingRepository;

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

    private List<Building> setStarterBuildings() throws BuildingNotValidException {
    List<Building> kingdomBuildings = new ArrayList<>();
    Building mine = BuildingFactory.createBuilding(BuildingType.mine);
    buildingRepository.save(mine);
    kingdomBuildings.add(mine);
    return kingdomBuildings;

  }
}
