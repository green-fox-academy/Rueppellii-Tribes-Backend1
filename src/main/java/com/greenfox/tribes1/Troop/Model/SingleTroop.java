package com.greenfox.tribes1.Troop.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "TestTroop")
@DiscriminatorValue("TestTroop")
@Getter
@Setter
public class SingleTroop extends Troop {

  @Override
  public void levelUp() {

  }
}
