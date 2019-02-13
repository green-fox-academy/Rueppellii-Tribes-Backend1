package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.TimeService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.sql.Timestamp;

@Entity(name = "Food")
@DiscriminatorValue("Food")
@NoArgsConstructor
@Getter
@Setter
public class Food extends KingdomResource {

  //TODO: remove @Transient somehow and make code cleaner
  //TODO: figure out how to remove TimeService
  @Transient
  TimeService timeService;

  public Food(Long amount) {
    this.setAmount(amount);
  }

  @Autowired
  public Food(TimeService timeService) {
    this.timeService = timeService;
  }

  public void setResourcePerMinute() {
    setAmountPerMinute(8L);
  }

  @Override
  @SneakyThrows
  public Long update() {
    return 2 * getBuilding().getLevel()
            * getAmountPerMinute()
            * timeService.calculateDifference(
            getUpdated_at(), new Timestamp(System.currentTimeMillis())
    ) + getAmount();
  }
}