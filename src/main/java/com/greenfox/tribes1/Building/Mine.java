package com.greenfox.tribes1.Building;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Mine")
@DiscriminatorValue("Mine")
@Getter
@Setter
public class Mine extends Building {

  public Mine() {
    this.setLevel(1L);
    this.setHP(200.0f);
  }
}
