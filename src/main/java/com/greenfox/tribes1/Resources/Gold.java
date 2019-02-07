package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Exception.DateNotGivenException;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.TimeService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class Gold extends KingdomResource implements Updatable {
  
  TimeService timeService;
  
  public Gold(Long id, Long amount, Timestamp updated_at, Long amountPerMinute, Building building, Kingdom kingdom, TimeService timeService) {
    super(id, amount, updated_at, amountPerMinute, building, kingdom);
    this.timeService = timeService;
    
  }
  
  public Gold(Long amount) {
    this.setAmount(amount);
  }
  
  public Gold(TimeService timeService) {
    this.timeService = timeService;
  }
  
  public void setResourcePerMinute() {
    setAmountPerMinute(12L);
  }
  
  @Override
  @SneakyThrows(DateNotGivenException.class)
  public Long update() {
    return getBuilding().getLevel() * getAmountPerMinute() * timeService.calculateDifference(getUpdated_at(), new Timestamp(System.currentTimeMillis()));
  }
}