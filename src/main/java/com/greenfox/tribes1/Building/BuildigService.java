package com.greenfox.tribes1.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildigService {

  @Autowired
  private BuildingRepository buildingRepository;

  public boolean isValidBuilding(Building building){
    return building!=null;
  }

  public void saveBuilding(Building building){
    if(isValidBuilding(building)){
      buildingRepository.save(building);
    }
  }

}
