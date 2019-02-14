package com.greenfox.tribes1.Resources;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity(name = "Gold")
@Getter
@Setter
@Component
public class Gold extends Resource {

  public Gold(Long amount) {
    this.setAmount(amount);
    this.setAmountPerMinute(10L);
  }

  public Gold() {
    this.setAmountPerMinute(20L);
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