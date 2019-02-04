package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Troop.Model.Archer;
import com.greenfox.tribes1.Troop.Model.Swordsman;
import com.greenfox.tribes1.Troop.Model.TestTroop;
import com.greenfox.tribes1.Troop.Model.Troop;

public enum TroopType {
  
  TestTroop {
    @Override
    public Troop makeTroop() {
      return new TestTroop();
    }
  },
  
  archer {
    @Override
    public Troop makeTroop() {
      return new Archer();
    }
  },
  swordsman {
    @Override
    public Troop makeTroop() {
      return new Swordsman();
    }
  };
  
  public Troop makeTroop() {
    return null;
  }
}
