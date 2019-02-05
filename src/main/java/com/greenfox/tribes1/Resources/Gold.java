package com.greenfox.tribes1.Resources;

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
  
  public void setResourcePerMinute() {
    setAmountPerMinute(12L);
  }
  
  @Override
  @SneakyThrows
  public Long update() {
    return getBuilding().getLevel() * getAmountPerMinute() * timeService.calculateDifference(getUpdated_at(), new Timestamp(System.currentTimeMillis()));
  }
}
