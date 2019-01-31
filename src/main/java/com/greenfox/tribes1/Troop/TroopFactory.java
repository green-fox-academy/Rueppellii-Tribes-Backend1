package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Troop.Model.Troop;

public class TroopFactory {

  public Troop makeTroop(TroopType troopType) {
    return troopType.makeTroop();
  }
}
