package com.greenfox.tribes1;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Service
public class TimeService {

  public Long calculateDifference(Timestamp started_at, Timestamp finished_at) throws DateNotGivenException {
    if (isTimestampValid(started_at) && (isTimestampValid(finished_at))) {
      long difference = finished_at.getTime() - started_at.getTime();
      return TimeUnit.MILLISECONDS.toMinutes(difference);
    }
    return null;
  }
  
//  Todo Upgrade model instead of Timestamp for input - for timestamp --> upgradeModel.getTimestamp
//  Todo use the buildingTime method in it
  public Timestamp calculateBuildingTimeForNewBuildingOrTroop (Timestamp started_at) throws DateNotGivenException {
    if (isTimestampValid(started_at)) {
      Long buildingTime = 1L;
      return new Timestamp(started_at.getTime() + TimeUnit.MINUTES.toMillis(buildingTime));
    }
    return null;
  }

  public Boolean isTimestampValid (Timestamp timestamp) throws DateNotGivenException {
    if (timestamp == null) {
      throw new DateNotGivenException("Date not given!");
    }
    return true;
  }

//  Todo implement method, when UpgradeModel is ready
//  public Long buildingTime (UpgradeModel upgradeModel) {
//    --> new buildig or troop: 1 min
//    --> upgrade buildig: LVL * 5 min
//    --> upgrade troop: LVL * 1 min
//  }
}
