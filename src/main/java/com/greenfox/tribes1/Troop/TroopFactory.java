package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Resources.Food;
import com.greenfox.tribes1.Troop.Model.Troop;

public class TroopFactory {
  
  public Troop makeTroop(TroopType troopType) {
    
    Food food = (Food) troopType.makeTroop()
        .getKingdom()
        .getResources()
        .stream()
        .filter(x -> x instanceof Food)
        .filter(filtered -> filtered.getAmount() > 0);
    food.setAmountPerMinute(food.getAmountPerMinute() - 1);
    
    return troopType.makeTroop();
  }
}
