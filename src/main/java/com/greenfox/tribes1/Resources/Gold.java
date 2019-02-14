package com.greenfox.tribes1.Resources;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name = "Gold")
@Getter
@Setter
public class Gold extends Resource {

  public Gold() {
    this.setAmountPerMinute(20L);
  }
}