package com.greenfox.tribes1.Resources;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Food")
@DiscriminatorValue("Food")
@Getter
@Setter
@Component
public class Food extends Resource {

  public Food() {
    this.setAmountPerMinute(8L);
  }
}


