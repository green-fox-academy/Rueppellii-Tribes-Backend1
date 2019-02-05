package com.greenfox.tribes1.Troop;

import com.google.common.collect.Iterables;
import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.Resources.Food;
import com.greenfox.tribes1.Resources.ResourceService;
import com.greenfox.tribes1.Troop.Model.Troop;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class TroopFactory {
  
  ResourceService resourceService;
  
  @Autowired
  public TroopFactory(ResourceService resourceService) {
    this.resourceService = resourceService;
  }
  
  public Troop makeTroop(TroopType troopType) throws NotValidResourceException {
    
    //List<Food> feed =
    Food food =
        Iterables.getOnlyElement(
            troopType.makeTroop()
                .getKingdom()
                .getResources()
                .stream()
                .filter(resource -> resource instanceof Food)
                .map(f -> (Food) f)
                .collect(Collectors.toList()));
    
    //Preconditions.checkArgument(feed.size() == 1); Not needed due to getOnlyElement
    
    food.setAmountPerMinute(food.getAmountPerMinute() - 1);
    resourceService.saveResource(food);
    
    return troopType.makeTroop();
  }
}