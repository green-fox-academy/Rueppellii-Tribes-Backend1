package com.greenfox.tribes1.kingdomelement.Resources;

public enum ResourceType {
  food {
    @Override
    public Resource generateResource() {
      return new Food();
    }
  },

  gold {
    @Override
    public Resource generateResource() {
      return new Gold();
    }
  };

  public Resource generateResource() {
    return null;
  }
}