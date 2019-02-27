package com.greenfox.tribes1.Progression;

import com.google.common.collect.Iterables;
import com.greenfox.tribes1.Exception.*;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Progression.DTO.ProgressionDTO;
import com.greenfox.tribes1.TimeService;
import com.greenfox.tribes1.Upgradable;
import com.greenfox.tribes1.kingdomelement.Building.Building;
import com.greenfox.tribes1.kingdomelement.Building.BuildingFactory;
import com.greenfox.tribes1.kingdomelement.Building.BuildingService;
import com.greenfox.tribes1.kingdomelement.Building.BuildingType;
import com.greenfox.tribes1.kingdomelement.Resources.Food;
import com.greenfox.tribes1.kingdomelement.Resources.Resource;
import com.greenfox.tribes1.kingdomelement.Resources.ResourceService;
import com.greenfox.tribes1.kingdomelement.Troop.Model.Troop;
import com.greenfox.tribes1.kingdomelement.Troop.TroopFactory;
import com.greenfox.tribes1.kingdomelement.Troop.TroopService;
import com.greenfox.tribes1.kingdomelement.Troop.TroopType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgressionService<T> implements Upgradable<Progression> {

  private ProgressionRepository progressionRepository;
  private TimeService timeService;
  private BuildingService buildingService;
  private TroopService troopService;
  private ResourceService resourceService;
  private Long level = 0L;
  private T t;

  @Autowired
  public ProgressionService(ProgressionRepository progressionRepository, TimeService timeService, BuildingService buildingService, TroopService troopService, ResourceService resourceService) {
    this.progressionRepository = progressionRepository;
    this.timeService = timeService;
    this.buildingService = buildingService;
    this.troopService = troopService;
    this.resourceService = resourceService;

  }

  //TODO: do it without generics

  public void addBuildingToKingdom(Progression progression, Building newBuilding) throws NotValidKingdomNameException, BuildingNotValidException, BuildingIdNotFoundException {
    Kingdom kingdomAddTo = progression.getKingdom();
    List<Building> buildingsOfKingdom = kingdomAddTo.getBuildings();
    buildingsOfKingdom.add(newBuilding);
    newBuilding.setKingdom(kingdomAddTo);
    kingdomAddTo.setBuildings(buildingsOfKingdom);
    buildingService.save(Optional.of(newBuilding));
  }

  public void finishConstructions() throws BuildingIdNotFoundException, NotValidKingdomNameException, BuildingNotValidException {
    List<Progression> finishedOnes = listOfThingsToCreateWithExpiredTimestamp();
    for (int i = 0; i < finishedOnes.size(); i++) {
      addBuildingToKingdom(finishedOnes.get(i), createNewBuilding(finishedOnes.get(i)));
    }
  }

  public void finishTroopConstructions() throws NotValidKingdomNameException, TroopNotValidException, NotValidResourceException {
    List<Progression> troops = listOfThingsToCreateWithExpiredTimestamp("Troop");
    for (Progression troop : troops) {
      addTroopToKingdom(troop, createNewTroop(troop));
    }
  }

  public void upgrade(Progression progression, T t) throws NotValidTypeException, TroopIdNotFoundException, BuildingIdNotFoundException {
    T thingToUpgrade = getExactBuildingOrTroop_FromProgressionModelId(progression);
  }

  public void upgradeTroop(Progression progression) throws NotValidTypeException, TroopIdNotFoundException, BuildingIdNotFoundException, TroopNotValidException {
    Troop troopToUpgrade = (Troop) getExactBuildingOrTroop_FromProgressionModelId((progression));
    troopService.upgrade(troopToUpgrade);
  }

  public void saveProgression(ProgressionDTO progressionDTO) {
    Progression progressionToSave = createProgressionFromDTO(progressionDTO);
    progressionToSave.setFinished(timeService.calculateBuildingTimeForNewBuildingOrTroop(progressionToSave));
    progressionRepository.save(progressionToSave);
  }

  public Progression createProgressionFromDTO(ProgressionDTO progressionDTO) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(progressionDTO, Progression.class);
  }

  public ProgressionDTO createProgressionDTOForCreation(Kingdom kingdom, String type) {
    ProgressionDTO progressionDTO = ProgressionDTO.builder()
            .type(type)
            .kingdom(kingdom)
            .level(0L)
            .model_id(0L).build();
    return progressionDTO;
  }

  public ProgressionDTO createProgressionDTOforBuildingUpgrade(Kingdom kingdom, Long id) throws BuildingIdNotFoundException {
    Building buildingToUpgrade = buildingService.findById(id);
    String type = buildingToUpgrade.getClass().getSimpleName();
    ProgressionDTO progressionDTO = ProgressionDTO.builder()
            .type(type)
            .kingdom(kingdom)
            .level(buildingToUpgrade.getLevel())
            .model_id(id).build();
    return progressionDTO;
  }

  public ProgressionDTO createProgressionDTOforTroopUpgrade(Kingdom kingdom, Long id) throws TroopIdNotFoundException {
    Troop troopToUpgrade = troopService.findById(id);
    ProgressionDTO progressionDTO = ProgressionDTO.builder()
            .type("troop")
            .kingdom(kingdom)
            .level(troopToUpgrade.getLevel())
            .model_id(id).build();
    return progressionDTO;
  }

  public void safeDeleteAllProgressionsWithExpiredTimestamp() {
    List<Progression> allExpired = listOfAllProgressionsWithExpiredTimestamp();
    for (Progression expired : allExpired) {
      expired.setKingdom(null);
      progressionRepository.delete(expired);
    }
  }

  public List<Progression> listOfThingsToCreateWithExpiredTimestamp(String type) {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    return progressionRepository.findByTypeAndFinishedIsLessThanAndLevelEquals(type, currentTime, level);
  }

  public List<Progression> listOfThingsToUpgradeeWithExpiredTimestamp(String type) {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    return progressionRepository.findByTypeAndFinishedIsLessThanAndLevelGreaterThan(type, currentTime, level);
  }

  public List<Progression> listOfAllProgressionsWithExpiredTimestamp() {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    return progressionRepository.findByFinishedLessThan(currentTime);
  }

  public void checkConstruction() throws NotValidKingdomNameException, TroopIdNotFoundException, BuildingIdNotFoundException, NotValidTypeException, BuildingNotValidException, TroopNotValidException, NotValidResourceException {
    /*finishMineConstructions();
    finishFarmConstructions();
    finishBarracksConstructions();*/
    finishConstructions();
    finishTroopConstructions();
    finishBuildingUpgrade();
    finishTroopUpgrade();
    safeDeleteAllProgressionsWithExpiredTimestamp();
  }

  public void finishBuildingUpgrade() {
    List<Progression> buildings = listOfThingsToCreateWithExpiredTimestamp("building");
    for (Progression building : buildings) {
      upgrade(building);
    }
  }

  public void finishTroopUpgrade() throws TroopIdNotFoundException, NotValidTypeException, BuildingIdNotFoundException, TroopNotValidException {
    List<Progression> troops = listOfThingsToUpgradeeWithExpiredTimestamp("troop");
    for (Progression troop : troops) {
      upgradeTroop(troop);
    }
  }

  public Building createNewBuilding(Progression progression) {
    String type = progression.getType();
    return BuildingFactory.createBuilding(BuildingType.valueOf(type));
  }

  public Troop createNewTroop(Progression progression) throws NotValidResourceException {
    String type = progression.getType();
    decreaseFood(progression);
    return TroopFactory.createTroop(TroopType.valueOf(type));
  }

  public Progression findById(Long id) throws ProgressionIdNotFoundException {
    return Optional.of(progressionRepository.findById(id)).get().orElseThrow(()
            -> new ProgressionIdNotFoundException(("There is no Troop with such Id")));
  }

  public Boolean isTypeBuilding(Progression progression) {
    return (progression.getType().equals("Barracks") ||
            progression.getType().equals("Farm") ||
            progression.getType().equals("Mine") ||
            progression.getType().equals("TownHall")
    );
  }

  public Boolean isTypeTroop(Progression progression) {
    return progression.getType().equals("troop");
  }

  public Object getExactBuildingOrTroop_FromProgressionModelId(Progression progression) throws BuildingIdNotFoundException, TroopIdNotFoundException, NotValidTypeException {
    //TODO: !A || !B --> needs to be corrected
    if (!isTypeBuilding(progression) || (!isTypeTroop(progression))) {
      throw new NotValidTypeException("Invalid Troop or Building Type");
    } else if (isTypeBuilding(progression)) {
      return buildingService.findById(progression.getModel_id());
    }
    return troopService.findById(progression.getModel_id());
  }

  public void addTroopToKingdom(Progression progression, Troop newTroop) throws NotValidKingdomNameException, TroopNotValidException {
    Kingdom kingdomAddTo = progression.getKingdom();
    List<Troop> troopsOfKingdom = kingdomAddTo.getTroops();

    newTroop.setKingdom(kingdomAddTo);
    troopsOfKingdom.add(newTroop);
    kingdomAddTo.setTroops(troopsOfKingdom);
    troopService.save(Optional.of(newTroop));
  }

  public void decreaseFood(Progression progression) throws NotValidResourceException {
    Resource food = Iterables.getOnlyElement(progression
            .getKingdom()
            .getResources()
            .stream()
            .filter(r -> r instanceof Food)
            .collect(Collectors.toList()));

    food.setAmount(food.getAmountPerMinute() - 1);
    resourceService.save(Optional.of(food));
  }

  @Override
  public void upgrade(Progression progression) {

  }
}