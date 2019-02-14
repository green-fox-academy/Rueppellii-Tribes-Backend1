package com.greenfox.tribes1;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import com.greenfox.tribes1.Progression.Progression;
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

  public Boolean isTimestampExpired(Timestamp timestamp) {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    return currentTime.after(timestamp);
  }

  public Timestamp calculateBuildingTimeForNewBuildingOrTroop(Progression progression) {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    Long buildingTime = buildingTime(progression);
    return new Timestamp(currentTime.getTime() + TimeUnit.MINUTES.toMillis(buildingTime));
  }

  public Long buildingTime(Progression progression) {
    if (progression.getLevel() == 0) {
      return 1L;
    } else if (progression.getLevel() != 0 && progression.getType().equals("troop")) {
      return progression.getLevel();
    } else {
      //time should be changed to 5 mins
      return progression.getLevel() * 1;
    }
  }

  public Boolean isTimestampValid(Timestamp timestamp) throws DateNotGivenException {
    if (timestamp == null) {
      throw new DateNotGivenException("Date not given!");
    }
    return true;
  }
}

