package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Troop.Model.Archer;
import com.greenfox.tribes1.Troop.Model.SingleTroop;
import com.greenfox.tribes1.Troop.Model.Swordsman;
import com.greenfox.tribes1.Troop.Model.Troop;

public enum TroopType {

  troop {
    @Override
    public Troop makeTroop() {
      return new SingleTroop();
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
