package com.greenfox.tribes1;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Service
public class TimeService {

  public Long calculateDifference(Timestamp startedDate, Timestamp finishedDate) throws DateNotGivenException {
    if (finishedDate == null || startedDate == null) {
      throw new DateNotGivenException("Date not given!");
    }
    long difference = finishedDate.getTime() - startedDate.getTime();
    return TimeUnit.MILLISECONDS.toMinutes(difference);
  }
}
