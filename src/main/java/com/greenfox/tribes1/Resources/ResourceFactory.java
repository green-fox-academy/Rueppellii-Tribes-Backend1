package com.greenfox.tribes1.Resources;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceFactory {
  
  public KingdomResource getResource(String resourceType) {
    if (resourceType.equals("food")) {
      return new Food();
    }
    if (resourceType.equals("gold")) {
      return new Gold();
    }
    return null;
  }
}
