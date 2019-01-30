package com.greenfox.tribes1;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Service
public class TimeService {

  public Long calculateDifference(Timestamp started_at, Timestamp finished_at) throws DateNotGivenException {
    if (finished_at == null || started_at == null) {
      throw new DateNotGivenException("Date not given!");
    }
    long difference = finished_at.getTime() - started_at.getTime();
    return TimeUnit.MILLISECONDS.toMinutes(difference);
  }
}
