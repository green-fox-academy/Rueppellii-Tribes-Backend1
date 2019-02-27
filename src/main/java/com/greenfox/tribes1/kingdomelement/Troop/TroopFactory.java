package com.greenfox.tribes1.kingdomelement.Troop;

import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.kingdomelement.Resources.ResourceService;
import com.greenfox.tribes1.kingdomelement.Troop.Model.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TroopFactory {

  private ResourceService resourceService;

  @Autowired
  public TroopFactory(ResourceService resourceService) {
    this.resourceService = resourceService;
  }

  public TroopFactory() {

  }

  public static Troop createTroop(TroopType troopType) {
    return troopType.makeTroop();
  }

  public Troop makeTroop(TroopType troopType) throws NotValidResourceException {

    return troopType.makeTroop();
  }
}
