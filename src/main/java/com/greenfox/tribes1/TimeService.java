package com.greenfox.tribes1;

import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Building.BuildingService;
import com.greenfox.tribes1.Exception.BuildingIdNotFoundException;
import com.greenfox.tribes1.Exception.DateNotGivenException;
import com.greenfox.tribes1.Exception.TroopIdNotFoundException;
import com.greenfox.tribes1.Progression.Progression;
import com.greenfox.tribes1.Troop.Model.Troop;
import com.greenfox.tribes1.Troop.TroopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Service
public class TimeService {

  private BuildingService buildingService;
  private TroopService troopService;

  @Autowired
  public TimeService(BuildingService buildingService, TroopService troopService) {
    this.buildingService = buildingService;
    this.troopService = troopService;
  }

  public Long calculateDifference(Timestamp started_at, Timestamp finished_at) throws DateNotGivenException {
    if (isTimestampValid(started_at) && (isTimestampValid(finished_at))) {
      long difference = finished_at.getTime() - started_at.getTime();
      return TimeUnit.MILLISECONDS.toMinutes(difference);
    }
    return null;
  }

  public Boolean isTimestampExpired(Timestamp timestamp) {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    return currentTime.getTime() > timestamp.getTime();
  }

  public Timestamp calculateBuildingTimeForNewBuildingOrTroop(Progression progression) throws TroopIdNotFoundException, BuildingIdNotFoundException {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    Long buildingTime = buildingTime(progression);
    return new Timestamp(currentTime.getTime() + TimeUnit.MINUTES.toMillis(buildingTime));
  }

  //  Todo extend method, to Upgrade by progressiontype and LVL
  public Long buildingTime(Progression progression) throws BuildingIdNotFoundException, TroopIdNotFoundException {
    if (progression.isCreate()) {
      return 1L;
    } else if (isTypeBuilding(progression)) {
      Building building = buildingService.findById(progression.getModel_id());
      return building.getLevel() * 5L;
    } else if (isTypeTroop(progression)) {
      Troop troop = troopService.findById(progression.getModel_id());
      return troop.getLevel();
    }
    return null;
  }
  // temporary solution, *warning* duplication alert!
  public Boolean isTypeBuilding(Progression progression) {
    return (progression.getType().equals("barracks") ||
            progression.getType().equals("farm") ||
            progression.getType().equals("mine"));
  }

  public Boolean isTypeTroop(Progression progression) {
    return progression.getType().equals("troop");
  }

  public Boolean isTimestampValid(Timestamp timestamp) throws DateNotGivenException {
    if (timestamp == null) {
      throw new DateNotGivenException("Date not given!");
    }
    return true;
  }
}
