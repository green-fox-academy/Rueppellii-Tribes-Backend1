package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import com.greenfox.tribes1.TimeService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
class Gold extends KingdomResource implements Updatable {
  
  TimeService timeService;
  
  public void setResourcePerMinute() {
    setAmountPerMinute(12L);
  }
  
  @Override
  public Long update() throws DateNotGivenException {
    return getBuilding().getLevel() * getAmountPerMinute() * timeService.calculateDifference(getUpdated_at(), new Timestamp(System.currentTimeMillis()));
  }
}
