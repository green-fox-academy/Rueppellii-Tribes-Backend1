package com.greenfox.tribes1.kingdomelement.Building;

public enum BuildingType {

  Farm {
    @Override
    public Building makeBuilding() {
      return new Farm();
    }
  },

  Mine {
    @Override
    public Building makeBuilding() {
      return new Mine();
    }
  },

  Barracks {
    @Override
    public Building makeBuilding() {
      return new Barracks();
    }
  },

  TownHall {
    @Override
    public Building makeBuilding() {
      return new TownHall();
    }
  };

  public Building makeBuilding() {
    return null;
  }
}
