package com.greenfox.tribes1.Resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
class Gold extends KingdomResource {
  
  public void setResourcePerMinute() {
    setResourcePerMinute(12L);
  }
}
