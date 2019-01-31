package com.greenfox.tribes1.Resources;

public enum ResourceType {
  food {
    public KingdomResource generateResource() {
      return new Food();
    }
  },
  
  gold {
    public KingdomResource generateResource() {
      return new Gold();
    }
  };
  
  public KingdomResource generateResource() {
    return null;
  }
}