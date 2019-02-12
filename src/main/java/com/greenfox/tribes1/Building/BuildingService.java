package com.greenfox.tribes1.Building;

import com.greenfox.tribes1.Exception.BuildingIdNotFoundException;
import com.greenfox.tribes1.Exception.BuildingNotValidException;
import com.greenfox.tribes1.KingdomElementService;
import com.greenfox.tribes1.Upgradable;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuildingService implements KingdomElementService<Building>, Upgradable<Building> {

  private BuildingRepository buildingRepository;
  //private Predicate<Building> isValid = (a) -> (a != null);

  @Autowired
  public BuildingService(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
  }

  /*public boolean isValidBuilding(Building building) {
    return building != null;
  }*/
  /*  //Todo TRB-29
  public void upgradeBarracks(Building buildingToUpgrade) throws BuildingNotValidException {
    buildingToUpgrade.setLevel(buildingToUpgrade.getLevel() + 1L);
    buildingToUpgrade.setHP(buildingToUpgrade.getHP() * 1.1F);
    save(buildingToUpgrade);
  }

  //Todo TRB-49
  public void upgradeFarm(Building buildingToUpgrade) throws BuildingNotValidException {
    buildingToUpgrade.setLevel(buildingToUpgrade.getLevel() + 1L);
    buildingToUpgrade.setHP(buildingToUpgrade.getHP() * 1.1F);
    save(buildingToUpgrade);
  }

  //Todo TRB-50
  public void upgradeMine(Building buildingToUpgrade) throws BuildingNotValidException {
    buildingToUpgrade.setLevel(buildingToUpgrade.getLevel() + 1L);
    buildingToUpgrade.setHP(buildingToUpgrade.getHP() * 1.1F);
    save(buildingToUpgrade);
  }*/

  @Override
  public void upgrade(Building building) {
    //TODO: checkBuildingType() ++ separate counting logic to somewhere else;
    //TODO: problem: if building.setHP breaks, the level remains set, but the HP doesn't
    //TODO: atomicity
    //TODO: implement building.upgrade();
    building.setLevel(building.getLevel() + 1L);
    building.setHP(building.getHP() * 1.1F);
    save(Optional.of(building));
  }

  @Override
  public Building findById(Long id) throws BuildingIdNotFoundException {
    //TODO: findById throws IllegalArgumentException
    //TODO: .orElseThrow may never be executed
    return buildingRepository.findById(id)
            .orElseThrow(() -> new BuildingIdNotFoundException(("There is no Building with such Id")));
  }

  @Override
  @SneakyThrows
  public Building save(Optional<Building> building) {
    return buildingRepository.save(building
            .orElseThrow(() -> new BuildingNotValidException("Building is not valid")));
  }

  @Override
  public void update(Building building) {
    //TODO: discuss how we should update our buildings
  }
}