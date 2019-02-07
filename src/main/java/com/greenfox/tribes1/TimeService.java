package com.greenfox.tribes1;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import com.greenfox.tribes1.Progression.Progression;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
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

  public Boolean isTimestampExpired (Timestamp timestamp) {
    Instant currentTime = Instant.now();
    Long timestampMillis = currentTime.toEpochMilli();
    Timestamp current = new Timestamp(timestampMillis);
    return current.getTime() > timestamp.getTime();
  }

  public Timestamp calculateBuildingTimeForNewBuildingOrTroop (Progression progression){
    Instant currentTime = Instant.now();
    Long timestampMillis = currentTime.toEpochMilli();
    Long buildingTime = buildingTime(progression);
    return new Timestamp(timestampMillis + TimeUnit.MINUTES.toMillis(buildingTime));

  }

//  Todo extend method, to Upgrade by progressiontype and LVL
  public Long buildingTime (Progression progression) {
    if (progression.isCreate()) {
      return 1L;
    } //else
//    --> new buildig or troop: 1 min DONE
//    --> upgrade buildig: LVL * 5 min TODO
//    --> upgrade troop: LVL * 1 min TODO
    return null;
  }

  public Boolean isTimestampValid (Timestamp timestamp) throws DateNotGivenException {
    if (timestamp == null) {
      throw new DateNotGivenException("Date not given!");
    }
    return true;
  }
}
