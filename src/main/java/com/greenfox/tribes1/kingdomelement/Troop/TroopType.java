package com.greenfox.tribes1.kingdomelement.Troop;

import com.greenfox.tribes1.kingdomelement.Troop.Model.SingleTroop;
import com.greenfox.tribes1.kingdomelement.Troop.Model.Troop;

public enum TroopType {

  troop {
    @Override
    public Troop makeTroop() {
      return new SingleTroop();
    }
  };

  public Troop makeTroop() {
    return null;
  }
}
