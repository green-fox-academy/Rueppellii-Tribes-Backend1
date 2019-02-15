package com.greenfox.tribes1;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import com.greenfox.tribes1.Progression.Progression;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@Service
public class TimeService {

  Predicate<Timestamp> isValid = Objects::nonNull;

  public Long calculateDifference(Timestamp started_at, Timestamp finished_at) throws DateNotGivenException {
    //TODO: try to rewrite with optional (pay attention that there are two possible NULL values
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
    } else if (progression.getType().equals("troop")) {
      return progression.getLevel();
    } else {
      //TODO: time should be changed to 5 mins
      return progression.getLevel() * 1;
    }
  }

  public Boolean isTimestampValid(Timestamp timestamp) throws DateNotGivenException {
    //TODO: if everything is done with optional, it can be wiped out
    if (timestamp == null) {
      throw new DateNotGivenException("Date not given!");
    }
    return true;
  }
}

