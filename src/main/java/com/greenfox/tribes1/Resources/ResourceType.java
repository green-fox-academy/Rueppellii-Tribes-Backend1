package com.greenfox.tribes1.Resources;

public enum ResourceType {
  food {
    @Override
    public KingdomResource generateResource() {
      return new Food();
    }
  },
  
  gold {
    @Override
    public KingdomResource generateResource() {
      return new Gold();
    }
  };
  
  public KingdomResource generateResource() {
    return null;
  }
}