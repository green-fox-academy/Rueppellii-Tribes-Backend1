package com.greenfox.tribes1.Building;

import com.greenfox.tribes1.Exception.BuildingIdNotFoundException;
import com.greenfox.tribes1.Exception.BuildingNotValidException;
import com.greenfox.tribes1.KingdomElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuildingService implements KingdomElementService<Building> {

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
    } else {
      throw new BuildingNotValidException("Building is not valid");
    }
  }

  public Building findById(Long id) throws BuildingIdNotFoundException {
    return Optional.of(buildingRepository.findById(id)).get().orElseThrow(()
            -> new BuildingIdNotFoundException(("There is no Building with such Id")));
  }

  //Todo TRB-29
  public void upgradeBarracks(Building buildingToUpgrade) throws BuildingNotValidException {
    buildingToUpgrade.setLevel(buildingToUpgrade.getLevel() + 1L);
    buildingToUpgrade.setHP(buildingToUpgrade.getHP() * 1.1F);
    saveBuilding(buildingToUpgrade);
  }

  //Todo TRB-49
  public void upgradeFarm(Building buildingToUpgrade) throws BuildingNotValidException {
    buildingToUpgrade.setLevel(buildingToUpgrade.getLevel() + 1L);
    buildingToUpgrade.setHP(buildingToUpgrade.getHP() * 1.1F);
    saveBuilding(buildingToUpgrade);
  }

  //Todo TRB-50
  public void upgradeMine(Building buildingToUpgrade) throws BuildingNotValidException {
    buildingToUpgrade.setLevel(buildingToUpgrade.getLevel() + 1L);
    buildingToUpgrade.setHP(buildingToUpgrade.getHP() * 1.1F);
    saveBuilding(buildingToUpgrade);
  }

  @Override
  public Building upgrade() {
    return null;
  }

  @Override
  public Building isValid() {
    return null;
  }

  @Override
  public Building findById() {
    return null;
  }

  @Override
  public Building save() {
    return null;
  }
}
