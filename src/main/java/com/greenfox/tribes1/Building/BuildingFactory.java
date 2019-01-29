package com.greenfox.tribes1.Building;

public class BuildingFactory {

  public static Building getBuilding(String type) {
    switch (type.toLowerCase()) {
      case "townhall":
        return new Townhall();
      case "mine":
        return new Mine();
      case "farm":
        return new Farm();
      case "barracks":
        return new Barracks();
      default:
        return null;
    }
  }
}
