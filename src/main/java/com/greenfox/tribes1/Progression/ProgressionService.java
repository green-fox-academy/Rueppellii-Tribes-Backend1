package com.greenfox.tribes1.Progression;

import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Building.BuildingFactory;
import com.greenfox.tribes1.Building.BuildingService;
import com.greenfox.tribes1.Building.BuildingType;
import com.greenfox.tribes1.Exception.*;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Kingdom.KingdomService;
import com.greenfox.tribes1.Progression.DTO.ProgressionDTO;
import com.greenfox.tribes1.TimeService;
import com.greenfox.tribes1.Troop.Model.Troop;
import com.greenfox.tribes1.Troop.TroopFactory;
import com.greenfox.tribes1.Troop.TroopService;
import com.greenfox.tribes1.Troop.TroopType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressionService {

  private ProgressionRepository progressionRepository;
  private TimeService timeService;
  private KingdomService kingdomService;
  private BuildingService buildingService;
  private TroopService troopService;

  private Long level = 0L;
//  private Timestamp currentTime = new Timestamp(System.currentTimeMillis());


  @Autowired
  public ProgressionService(ProgressionRepository progressionRepository, TimeService timeService, KingdomService kingdomService, BuildingService buildingService, TroopService troopService) {
    this.progressionRepository = progressionRepository;
    this.timeService = timeService;
    this.kingdomService = kingdomService;
    this.buildingService = buildingService;
    this.troopService = troopService;
  }

//  TODO:
//    saveProgression()
//
//  TODO:
//    checkConstructions()
//    finishMineConstructions()
//    finishFarmConstructions()
//    finishBarracksConstructions()
//    finishTroopConstructions()
//    finishMineUpgrade()
//    finishFarmUpgrade()
//    finishBarracksUpgrade()
//    finishTroopUpgrade()
//
//  TODO:
//    listOfMinesWithExpiredTimestamp()
//    listOfFarmsWithExpiredTimestamp()
//    listOfBarracksWithExpiredTimestamp()
//    listOfTroopsWithExpiredTimestamp()
//
//  TODO:
//    createBuilding(Progression progression)
//    createTroop(Progression progression)
//  BUT
//    uprgadeMine(Progression progression)
//    uprgadeFarm(Progression progression)
//    uprgadeBarracks(Progression progression)
//    uprgadeTroop(Progression progression)
//
//  TODO:
//    addBuildingToKingdom(Progression progression, Building newBuilding)
//    addTroopToKingdom(Progression progression, Troop newTroop)

  public void saveProgression(ProgressionDTO progressionDTO) {
    Progression progressionToSave = createProgressionFromDTO(progressionDTO);
    progressionToSave.setFinished(timeService.calculateBuildingTimeForNewBuildingOrTroop(progressionToSave));
    progressionRepository.save(progressionToSave);
  }

  public Progression createProgressionFromDTO(ProgressionDTO progressionDTO) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(progressionDTO, Progression.class);
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

  public void checkConstruction() throws NotValidKingdomNameException, TroopIdNotFoundException, BuildingIdNotFoundException, NotValidTypeException, BuildingNotValidException, TroopNotValidException {
    finishMineConstructions();
    finishFarmConstructions();
    finishBarracksConstructions();
    finishTroopConstructions();
    finishMineUpgrade();
    finishFarmUpgrade();
    finishBarracksUpgrade();
    finishTroopUpgrade();
    safeDeleteAllProgressionsWithExpiredTimestamp();
  }

  public void finishMineConstructions() throws NotValidKingdomNameException, BuildingNotValidException, BuildingIdNotFoundException {
    List<Progression> mines = listOfThingsToCreateWithExpiredTimestamp("mine");
    for (Progression mine : mines) {
      addBuildingToKingdom(mine, createNewBuilding(mine));
    }
  }

  public void finishFarmConstructions() throws NotValidKingdomNameException, BuildingNotValidException, BuildingIdNotFoundException {
    List<Progression> farms = listOfThingsToCreateWithExpiredTimestamp("farm");
    for (Progression farm : farms) {
      addBuildingToKingdom(farm, createNewBuilding(farm));
    }
  }

  public void finishBarracksConstructions() throws NotValidKingdomNameException, BuildingNotValidException, BuildingIdNotFoundException {
    List<Progression> barracks = listOfThingsToCreateWithExpiredTimestamp("barracks");
    for (Progression barrack : barracks) {
      addBuildingToKingdom(barrack, createNewBuilding(barrack));
    }
  }

  public void finishTroopConstructions() throws NotValidKingdomNameException, TroopNotValidException {
    List<Progression> troops = listOfThingsToCreateWithExpiredTimestamp("troop");
    for (Progression troop : troops) {
      addTroopToKingdom(troop, createNewTroop(troop));
    }
  }

  public void finishMineUpgrade() throws TroopIdNotFoundException, BuildingNotValidException, NotValidTypeException, BuildingIdNotFoundException {
    List<Progression> mines = listOfThingsToUpgradeeWithExpiredTimestamp("mine");
    for (Progression mine : mines) {
      upgradeMine(mine);
    }
  }

  public void finishFarmUpgrade() throws TroopIdNotFoundException, BuildingNotValidException, NotValidTypeException, BuildingIdNotFoundException {
    List<Progression> farms = listOfThingsToUpgradeeWithExpiredTimestamp("farm");
    for (Progression farm : farms) {
      upgradeFarm(farm);
    }
  }

  public void finishBarracksUpgrade() throws TroopIdNotFoundException, BuildingNotValidException, NotValidTypeException, BuildingIdNotFoundException {
    List<Progression> barracks = listOfThingsToUpgradeeWithExpiredTimestamp("barracks");
    for (Progression barrack : barracks) {
      upgradeBarracks(barrack);
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

  public Troop createNewTroop(Progression progression) {
    String type = progression.getType();
    return TroopFactory.createTroop(TroopType.valueOf(type));
  }

  public void upgradeMine(Progression progression) throws NotValidTypeException, TroopIdNotFoundException, BuildingIdNotFoundException, BuildingNotValidException {
    Building buildingToUpgrade = (Building) getExactBuildingOrTroop_FromProgressionModelId(progression);
    buildingService.upgradeMine(buildingToUpgrade);
  }

  public void upgradeFarm(Progression progression) throws NotValidTypeException, TroopIdNotFoundException, BuildingIdNotFoundException, BuildingNotValidException {
    Building buildingToUpgrade = (Building) getExactBuildingOrTroop_FromProgressionModelId(progression);
    buildingService.upgradeFarm(buildingToUpgrade);
  }

  public void upgradeBarracks(Progression progression) throws NotValidTypeException, TroopIdNotFoundException, BuildingIdNotFoundException, BuildingNotValidException {
    Building buildingToUpgrade = (Building) getExactBuildingOrTroop_FromProgressionModelId(progression);
    buildingService.upgradeBarracks(buildingToUpgrade);
  }

  public void upgradeTroop(Progression progression) throws NotValidTypeException, TroopIdNotFoundException, BuildingIdNotFoundException, TroopNotValidException {
    Troop troopToUpgrade = (Troop) getExactBuildingOrTroop_FromProgressionModelId((progression));
    troopService.upgradeTroop(troopToUpgrade);
  }

  public Progression findById(Long id) throws ProgressionIdNotFoundException {
    return Optional.of(progressionRepository.findById(id)).get().orElseThrow(()
            -> new ProgressionIdNotFoundException(("There is no Troop with such Id")));
  }

  public Boolean isTypeBuilding(Progression progression) {
    return (progression.getType().equals("barracks") ||
            progression.getType().equals("farm") ||
            progression.getType().equals("mine"));
  }

  public Boolean isTypeTroop(Progression progression) {
    return progression.getType().equals("troop");
  }

  public Object getExactBuildingOrTroop_FromProgressionModelId(Progression progression) throws BuildingIdNotFoundException, TroopIdNotFoundException, NotValidTypeException {
    if (!isTypeBuilding(progression) || (!isTypeTroop(progression))) {
      throw new NotValidTypeException("Invalid Troop or Building Type");
    } else if (isTypeBuilding(progression)) {
      return buildingService.findById(progression.getModel_id());
    }
    return troopService.findById(progression.getModel_id());
  }

  //  Todo: Made by TRB-21 setStarterBuildings method's logic
  public void addBuildingToKingdom(Progression progression, Building newBuilding) throws NotValidKingdomNameException, BuildingNotValidException, BuildingIdNotFoundException {
    Kingdom kingdomAddTo = progression.getKingdom();
    List<Building> buildingsOfKingdom = kingdomAddTo.getBuildings();

//    buildingsOfKingdom.remove(1);
//    buildingService.removeById(2L);
    buildingsOfKingdom.add(newBuilding);
    newBuilding.setKingdom(kingdomAddTo);
    kingdomAddTo.setBuildings(buildingsOfKingdom);
//    kingdomService.saveKingdom(kingdomAddTo);
    buildingService.saveBuilding(newBuilding);
//    System.out.println(kingdomAddTo.getBuildings());
  }

  //  Todo: Made by TRB-21 setStarterBuildings method's logic
  public void addTroopToKingdom(Progression progression, Troop newTroop) throws NotValidKingdomNameException, TroopNotValidException {
    Kingdom kingdomAddTo = progression.getKingdom();
    List<Troop> troopsOfKingdom = kingdomAddTo.getTroops();

    newTroop.setKingdom(kingdomAddTo);
    troopsOfKingdom.add(newTroop);
    kingdomAddTo.setTroops(troopsOfKingdom);
//    kingdomService.saveKingdom(kingdomAddTo);
    troopService.save(newTroop);
  }

  //TODO Progression SQL!!!!!!
//  public void findProgressionsWithExpiredTimestamp_CreateOrUpgradeModelFromThem_DeleteThem() throws BuildingTypeNotValidException, TroopTypeNotValidException, TroopIdNotFoundException, BuildingNotValidException, NotValidTypeException, BuildingIdNotFoundException, TroopNotValidException {
//    List<Progression> allProgressionModelsList = findAllProgressionModel();
//    for (int i = 0; i < allProgressionModelsList.size(); i++) {
//      if (timeService.isTimestampExpired(allProgressionModelsList.get(i).getFinished())) {
//        if (isItBuildingToCreate(allProgressionModelsList.get(i))) {
//          createNewBuildingFromProgression_AndAddItToKingdom(allProgressionModelsList.get(i));
//        } else if (isItTroopToCreate(allProgressionModelsList.get(i))) {
//          createNewTroopFromProgression_AndAddItToKingdom(allProgressionModelsList.get(i));
//        } else if (isItBuildingToUpgrade(allProgressionModelsList.get(i))) {
//          upgradeBuildingFromProgression(allProgressionModelsList.get(i));
//        } else if (isItTroopToUpgrade(allProgressionModelsList.get(i))) {
//          upgradeTroopFromProgression(allProgressionModelsList.get(i));
//        }
//        safeDeleteProgression(allProgressionModelsList.get(i));
//      }
//    }
//  }

//  public Boolean isItBuildingToCreate(Progression progression) {
//    return progression.getLevel() == 0 && isTypeBuilding(progression);
//  }
//
//  public Boolean isItBuildingToUpgrade(Progression progression) {
//    return !(progression.getLevel() == 0) && isTypeBuilding(progression);
//  }
//
//  public Boolean isItTroopToCreate(Progression progression) {
//    return progression.getLevel() == 0 && isTypeTroop(progression);
//  }
//
//  public Boolean isItTroopToUpgrade(Progression progression) {
//    return !(progression.getLevel() == 0) && isTypeTroop(progression);
//  }


//  public void createNewBuildingFromProgression_AndAddItToKingdom(Progression progression) {
//    String type = progression.getType();
//    Building newBuilding = BuildingFactory.createBuilding(BuildingType.valueOf(type));
//    addBuildingToKingdom(progression, newBuilding);
//    if (type.equals("barracks")) {
//      Building newBuilding = BuildingFactory.createBuilding(BuildingType.barracks);
//      addBuildingToKingdom(progression, newBuilding);
//    } else if (type.equals("farm")) {
//      Building newBuilding = BuildingFactory.createBuilding(BuildingType.farm);
//      addBuildingToKingdom(progression, newBuilding);
//    } else if (type.equals("mine")) {
//      Building newBuilding = BuildingFactory.createBuilding(BuildingType.mine);
//      addBuildingToKingdom(progression, newBuilding);
//    } else {
//      throw new BuildingTypeNotValidException("There is no such type of building");
//    }
//  }
//
//  public void createNewTroopFromProgression_AndAddItToKingdom(Progression progression) {
//    String type = progression.getType();
//    Troop newTroop = TroopFactory.createTroop(TroopType.valueOf(type));
//    addTroopToKingdom(progression, newTroop);
//    if (type.equals("troop")) {
//      Troop newTroop = TroopFactory.createTroop(TroopType.TestTroop);
//      addTroopToKingdom(progression, newTroop);
//    } else {
//      throw new TroopTypeNotValidException("There is no such type of troop");
//    }
//  }

//  public void upgradeBuildingFromProgression(Progression progression) throws NotValidTypeException, TroopIdNotFoundException, BuildingIdNotFoundException, BuildingNotValidException {
//    Building buildingToUpgrade = (Building) getExactBuildingOrTroop_FromProgressionModelId(progression);
//    String buildingType = progression.getType();
//    if (buildingType.equals("barracks")) {
//      buildingService.upgradeBarracks(buildingToUpgrade);
//    } else if (buildingType.equals("farm")) {
//      buildingService.upgradeFarm(buildingToUpgrade);
//    } else if (buildingType.equals("mine")) {
//      buildingService.upgradeMine(buildingToUpgrade);
//    } else {
//      throw new NotValidTypeException("Invalid Building type");
//    }
//    //TODO TRB-29, 49, 50 for buildingUpgrade
//  }

//  public void upgradeTroopFromProgression(Progression progression) throws NotValidTypeException, TroopIdNotFoundException, BuildingIdNotFoundException, TroopNotValidException {
//    Troop troopToUpgrade = (Troop) getExactBuildingOrTroop_FromProgressionModelId((progression));
//    String troopType = progression.getType();
//    if (troopType.equals("troop")) {
//      troopService.upgradeTroop(troopToUpgrade);
//    } else {
//      throw new NotValidTypeException("Invalid Troop type");
//    }
//    //TODO TRB-48 (Dani) for troopUpgrade
//  }
}
