package com.greenfox.tribes1.Progression;

import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Building.BuildingFactory;
import com.greenfox.tribes1.Building.BuildingService;
import com.greenfox.tribes1.Building.BuildingType;
import com.greenfox.tribes1.Exception.*;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Kingdom.KingdomService;
import com.greenfox.tribes1.TimeService;
import com.greenfox.tribes1.Troop.Model.Troop;
import com.greenfox.tribes1.Troop.TroopFactory;
import com.greenfox.tribes1.Troop.TroopService;
import com.greenfox.tribes1.Troop.TroopType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressionService {
  private ProgressionRepository progressionRepository;
  private TimeService timeService;
  private KingdomService kingdomService;
  private BuildingService buildingService;
  private TroopService troopService;

  @Autowired
  public ProgressionService(ProgressionRepository progressionRepository, TimeService timeService, KingdomService kingdomService, BuildingService buildingService, TroopService troopService) {
    this.progressionRepository = progressionRepository;
    this.timeService = timeService;
    this.kingdomService = kingdomService;
    this.buildingService = buildingService;
    this.troopService = troopService;
  }

//  Todo: check what will we get exactly from frontend (Progression or Strings with ALL of the progressiondata)
//  Todo: modify method according to that
  public void saveProgression (Progression progression) {
    progression.setFinished_at(timeService.calculateBuildingTimeForNewBuildingOrTroop(progression)); //Todo: is it needed OR I get a complete progression from frontend?
    progressionRepository.save(progression);
  }

  public void safeDeleteProgression (Progression progression) {
    progression.setKingdom(null);
    progressionRepository.delete(progression);
  }

  public Progression findById (Long id) throws ProgressionIdNotFoundException {
    return Optional.of(progressionRepository.findById(id)).get().orElseThrow(()
            -> new ProgressionIdNotFoundException(("There is no Troop with such Id")));
  }

  public List<Progression> findAllProgressionModel() {
    return progressionRepository.findAll();
  }

//TODO Progression SQL!!!!!!
  public void findProgressionsWithExpiredTimestamp_CreateOrUpgradeModelFromThem_DeleteThem() throws BuildingTypeNotValidException, TroopTypeNotValidException, TroopIdNotFoundException, BuildingNotValidException, NotValidTypeException, BuildingIdNotFoundException, TroopNotValidException {
    List<Progression> allProgressionModelsList = findAllProgressionModel();
    for (int i = 0; i < allProgressionModelsList.size(); i++) {
      if (timeService.isTimestampExpired(allProgressionModelsList.get(i).getFinished_at())) {
        if (isItBuildingToCreate(allProgressionModelsList.get(i))) {
          createNewBuildingFromProgression_AndAddItToKingdom(allProgressionModelsList.get(i));
        } else if (isItTroopToCreate(allProgressionModelsList.get(i))) {
          createNewTroopFromProgression_AndAddItToKingdom(allProgressionModelsList.get(i));
        } else if (isItBuildingToUpgrade(allProgressionModelsList.get(i))) {
          upgradeBuildingFromProgression(allProgressionModelsList.get(i));
        } else if (isItTroopToUpgrade(allProgressionModelsList.get(i))) {
          upgradeTroopFromProgression(allProgressionModelsList.get(i));
        }
        safeDeleteProgression(allProgressionModelsList.get(i));
      }
    }
  }

  public Boolean isItBuildingToCreate (Progression progression) {
    return progression.isCreate() && isTypeBuilding(progression);
  }

  public Boolean isItBuildingToUpgrade (Progression progression) {
    return !progression.isCreate() && isTypeBuilding(progression);
  }

  public Boolean isItTroopToCreate (Progression progression) {
    return progression.isCreate() && isTypeTroop(progression);
  }

  public Boolean isItTroopToUpgrade (Progression progression) {
    return !progression.isCreate() && isTypeTroop(progression);
  }

  public Boolean isTypeBuilding (Progression progression) {
    return (progression.getType().equals("barracks") ||
            progression.getType().equals("farm") ||
            progression.getType().equals("mine"));
  }

  public Boolean isTypeTroop (Progression progression) {
    return progression.getType().equals("troop");
  }

  public void createNewBuildingFromProgression_AndAddItToKingdom (Progression progression) throws BuildingTypeNotValidException {
    String type = progression.getType();
    if (type.equals("barracks")) {
      Building newBuilding = BuildingFactory.createBuilding(BuildingType.barracks);
      addBuildingToKingdom(progression, newBuilding);
    } else if (type.equals("farm")) {
      Building newBuilding = BuildingFactory.createBuilding(BuildingType.farm);
      addBuildingToKingdom(progression, newBuilding);
    } else if (type.equals("mine")) {
      Building newBuilding = BuildingFactory.createBuilding(BuildingType.mine);
      addBuildingToKingdom(progression, newBuilding);
    } else throw new BuildingTypeNotValidException("There is no such type of building");
  }

  public void createNewTroopFromProgression_AndAddItToKingdom (Progression progression) throws BuildingTypeNotValidException, TroopTypeNotValidException {
    String type = progression.getType();
    if (type.equals("troop")) {
      Troop newTroop = TroopFactory.createTroop(TroopType.troop);
      addTroopToKingdom(progression, newTroop);
    } else throw new TroopTypeNotValidException("There is no such type of troop");
  }

  public void upgradeBuildingFromProgression (Progression progression) throws NotValidTypeException, TroopIdNotFoundException, BuildingIdNotFoundException, BuildingNotValidException {
    Building buildingToUpgrade = (Building) getExactBuildingOrTroop_FromProgressionModelId(progression);
    String buildingType = progression.getType();
    if (buildingType.equals("barracks")) {
      buildingService.upgradeBarracks(buildingToUpgrade);
    } else if (buildingType.equals("farm")) {
      buildingService.upgradeFarm(buildingToUpgrade);
    } else if (buildingType.equals("mine")) {
      buildingService.upgradeMine(buildingToUpgrade);
    } else throw new NotValidTypeException("Invalid Building type");
     //TODO TRB-29, 49, 50 for buildingUpgrade
  }

  public void upgradeTroopFromProgression (Progression progression) throws NotValidTypeException, TroopIdNotFoundException, BuildingIdNotFoundException, TroopNotValidException {
    Troop troopToUpgrade = (Troop) getExactBuildingOrTroop_FromProgressionModelId((progression));
    String troopType = progression.getType();
    if (troopType.equals("troop")) {
      troopService.upgradeTroop(troopToUpgrade);
    } else throw new NotValidTypeException("Invalid Troop type");
    //TODO TRB-48 (Dani) for troopUpgrade
  }

  public Object getExactBuildingOrTroop_FromProgressionModelId (Progression progression) throws BuildingIdNotFoundException, TroopIdNotFoundException, NotValidTypeException {
    if (!isTypeBuilding(progression) || (!isTypeTroop(progression)) ) {
      throw new NotValidTypeException("Invalid Troop or Building Type");
    } else if (isTypeBuilding(progression)) {
      return buildingService.findById(progression.getModel_id());
    }
    return troopService.findById(progression.getModel_id());
  }

//  Todo: Made by TRB-21 setStarterBuildings method's logic
  public void addBuildingToKingdom (Progression progression, Building newBuilding) {
    Kingdom kingdomAddTo = progression.getKingdom();
    List<Building> buildingsOfKingdom = kingdomAddTo.getBuildings();

    newBuilding.setKingdom(kingdomAddTo);
    buildingsOfKingdom.add(newBuilding);
    kingdomAddTo.setBuildings(buildingsOfKingdom);
  }

//  Todo: Made by TRB-21 setStarterBuildings method's logic
  public void addTroopToKingdom (Progression progression, Troop newTroop) {
    Kingdom kingdomAddTo = progression.getKingdom();
    List<Troop> troopsOfKingdom = kingdomAddTo.getTroops();

    newTroop.setKingdom(kingdomAddTo);
    troopsOfKingdom.add(newTroop);
    kingdomAddTo.setTroops(troopsOfKingdom);
  }
}
