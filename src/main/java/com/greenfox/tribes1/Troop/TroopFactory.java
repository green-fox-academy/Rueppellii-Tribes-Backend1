package com.greenfox.tribes1.Troop;

import com.google.common.collect.Iterables;
import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.Resources.Food;
import com.greenfox.tribes1.Resources.ResourceService;
import com.greenfox.tribes1.Troop.Model.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TroopFactory {
  
  ResourceService resourceService;
  
  @Autowired
  public TroopFactory(ResourceService resourceService) {
    this.resourceService = resourceService;
  }
  
  public Troop makeTroop(TroopType troopType) throws NotValidResourceException {
    
    //List<Food> food =
    Food food =
        Iterables.getOnlyElement( //casts List<Food> to Food as well
            troopType.makeTroop()
                .getKingdom()
                .getResources()
                .stream()
                .filter(resource -> resource instanceof Food)
                .map(f -> (Food) f)
                .collect(Collectors.toList()));
    
    //Preconditions.checkArgument(food.size() == 1); Not needed due to getOnlyElement
    
    food.setAmountPerMinute(food.getAmountPerMinute() - 1);
    resourceService.saveResource(food);
    
    return troopType.makeTroop();
  }
}