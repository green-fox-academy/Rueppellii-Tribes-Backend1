package com.greenfox.tribes1.Resources;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity(name = "Food")
@DiscriminatorValue("Food")
@Getter
@Setter
@Component
public class Food extends Resource {

  //TODO: remove @Transient somehow and make code cleaner
  //TODO: figure out how to remove TimeService

  public Food(Long amount) {
    this.setAmount(amount);
    this.setAmountPerMinute(10L);
  }

  public Food() {
    this.setAmountPerMinute(8L);
  }

  @Override
  @SneakyThrows
  public void update(Long difference) {
    System.out.println(getAmountPerMinute());
    System.out.println(difference);
    System.out.println(getAmount());
    if (difference > 0) {
      setAmount(getAmountPerMinute() * difference
              + getAmount());
      setUpdated_at(new Timestamp(System.currentTimeMillis()));
    }
  }

}
