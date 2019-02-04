package com.greenfox.tribes1.Troop;

import com.google.common.base.Preconditions;
import com.greenfox.tribes1.Resources.Food;
import com.greenfox.tribes1.Troop.Model.Troop;

import java.util.List;
import java.util.stream.Collectors;

public class TroopFactory {
  
  public Troop makeTroop(TroopType troopType) {
    
    List<Food> feed = troopType.makeTroop()
        .getKingdom()
        .getResources()
        .stream()
        .filter(resource -> resource instanceof Food)
        .map(f -> (Food) f)
        .collect(Collectors.toList());
    
    Preconditions.checkArgument(feed.size() == 1);
    
    Food food = feed.get(0);
    food.setAmountPerMinute(food.getAmountPerMinute() - 1);
    
    // TODO: save food
    
    return troopType.makeTroop();
  }
}