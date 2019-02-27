package com.greenfox.tribes1.kingdomelement;

import com.greenfox.tribes1.kingdomelement.Building.BuildingService;
import com.greenfox.tribes1.kingdomelement.Building.BuildingType;
import com.greenfox.tribes1.kingdomelement.Troop.TroopService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

@Getter
@Setter
public final class ModelRepositories {

  private final HashMap<String, KingdomElementService> connections;

  TroopService troopService;
  BuildingService buildingService;

  @Autowired
  public ModelRepositories(TroopService troopService, BuildingService buildingService) {
    this.troopService = troopService;
    this.buildingService = buildingService;
    this.connections = new HashMap<>();
    this.connections.put("Troop", troopService);
    for (BuildingType type : BuildingType.values()) {
      this.connections.put(type.toString(), buildingService);
    }
  }
}