package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.TimeService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.sql.Timestamp;

@Entity(name = "Gold")
@Getter
@Setter
@NoArgsConstructor
public class Gold extends Resource implements Updatable {

  @Autowired
  @Transient
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
  @SneakyThrows
  public Long update() {
    return getBuilding().getLevel() * getAmountPerMinute() * timeService.calculateDifference(getUpdated_at(), new Timestamp(System.currentTimeMillis()));
  }
}