package com.greenfox.tribes1.Resources;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity(name = "Gold")
@Getter
@Setter
@Component
public class Gold extends Resource {

  public Gold() {
    this.setAmountPerMinute(20L);
  }
}