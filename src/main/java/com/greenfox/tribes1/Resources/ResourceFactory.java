package com.greenfox.tribes1.Resources;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceFactory {
  
  public KingdomResource getResource(String resourceType) {
    if (ResourceFactory.isValid(resourceType)) {
      if (resourceType.equals("food")) {
        return new Food();
      }
      if (resourceType.equals("gold")) {
        return new Gold();
      }
    }
    return null;
  }
  
  private static Boolean isValid(String resourceType) {
    return (resourceType.equals("food") || resourceType.equals("gold"));
  }
}
