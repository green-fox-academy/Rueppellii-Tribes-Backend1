package com.greenfox.tribes1.Building;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Farm")
@DiscriminatorValue("Farm")
@Getter
@Setter
public class Farm extends Building {

  public Farm() {
    this.setLevel(1L);
    this.setHP(150.0f);
  }

  @Override
  void upgrade() {
    //TODO
  }}
