package com.greenfox.tribes1.Building;

public enum BuildingType {

  farm {
    public Building makeBuilding() {
      return new Farm();
    }
  },

  mine {
    public Building makeBuilding() {
      return new Mine();
    }
  },

  barracks {
    public Building makeBuilding(){return new Barracks();}
  },

  townhall {
    public Building makeBuilding() {
      return new Townhall();
    }
  };

  public Building makeBuilding() {
    return null;
  }
}
