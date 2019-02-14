package com.greenfox.tribes1.Purchase;

import com.google.common.collect.Iterables;
import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Building.BuildingService;
import com.greenfox.tribes1.Exception.*;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Resources.Gold;
import com.greenfox.tribes1.Resources.Resource;
import com.greenfox.tribes1.Resources.ResourceService;
import com.greenfox.tribes1.Troop.Model.Troop;
import com.greenfox.tribes1.Troop.TroopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

  private BuildingService buildingService;
  private ResourceService resourceService;
  private TroopService troopService;

  @Autowired
  public PurchaseService(BuildingService buildingService, ResourceService resourceService, TroopService troopService) {
    this.buildingService = buildingService;
    this.resourceService = resourceService;
    this.troopService = troopService;
  }

  public void purchase(Kingdom kingdom, Long id, Long upgradeCost) throws GoldNotEnoughException, NotValidResourceException {
    List<Resource> kingdomResources = kingdom.getResources();
    Gold gold = getGoldAmount(kingdomResources);
    purchaseIfPossible(gold, 1L, upgradeCost);
  }

  public void purchaseTroopUpgrade(Kingdom kingdom, Long id) throws GoldNotEnoughException, NotValidResourceException, TroopIdNotFoundException {
    Troop troop = troopService.findById(id);
    List<Resource> kingdomResources = kingdom.getResources();
    Gold gold = getGoldAmount(kingdomResources);
    Long upgradeTo = troop.getLevel() + 1L;
    Long troopUpgradeCost = 10L;

    purchaseIfPossible(gold, upgradeTo, troopUpgradeCost);
  }

  public void purchaseBuildingUpgrade(Kingdom kingdom, Long id) throws GoldNotEnoughException, BuildingIdNotFoundException, NotValidResourceException {
    Building building = buildingService.findById(id);
    List<Resource> kingdomResources = kingdom.getResources();
    Gold gold = getGoldAmount(kingdomResources);
    Long upgradeLevel = building.getLevel() + 1L;
    Long buildingUpgradeCost = 100L;

    purchaseIfPossible(gold, upgradeLevel, buildingUpgradeCost);
  }

  private Gold getGoldAmount(List<Resource> kingdomResources) {
    List<Resource> filteredResources = kingdomResources.stream().filter(r -> r instanceof Gold).collect(Collectors.toList());
    return (Gold) Iterables.getOnlyElement(filteredResources);
  }

  private Boolean isGoldEnough(Gold gold, Long upgradeCost) {
    return gold.getAmount() > upgradeCost;
  }

  private Resource purchaseIfPossible(Gold gold, Long upgradeTo, Long upgradeCost) throws NotValidResourceException, GoldNotEnoughException {
    if (isGoldEnough(gold, upgradeCost)) {
      Long newGoldAmount = gold.getAmount() - upgradeTo * upgradeCost;
      gold.setAmount(newGoldAmount);
      return resourceService.save(Optional.of(gold));
    }
    throw new GoldNotEnoughException("Not enough gold");
  }
}
