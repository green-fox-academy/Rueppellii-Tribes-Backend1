package com.greenfox.tribes1.Troop;

public class TroopFactory {

  public Troop makeTroop(String troopType) {
    if (troopType.equals("test")) {
      return new TestTroop();
    }
    return null;
  }
}
