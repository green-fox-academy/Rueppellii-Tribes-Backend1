package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import com.greenfox.tribes1.TimeService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class Food extends KingdomResource implements Updatable {
  
  TimeService timeService;
  
  public Food(Long amount) {
    this.setAmount(amount);
  }
  
  public Food(TimeService timeService) {
    this.timeService = timeService;
  }
  
  public void setResourcePerMinute() {
    setAmountPerMinute(8L);
  }
  
  @Override
  @SneakyThrows(DateNotGivenException.class)
  public Long update() {
    return 2 * getBuilding().getLevel() * getAmountPerMinute() * timeService.calculateDifference(getUpdated_at(), new Timestamp(System.currentTimeMillis()));
  }
}