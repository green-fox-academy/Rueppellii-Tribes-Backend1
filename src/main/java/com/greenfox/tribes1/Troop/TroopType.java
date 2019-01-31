package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Troop.Model.TestTroop;
import com.greenfox.tribes1.Troop.Model.Troop;

public enum TroopType {
  Test {
    public Troop makeTroop() {
      return new TestTroop();
    }
  };

  public Troop makeTroop() {
    return null;
  }
}
