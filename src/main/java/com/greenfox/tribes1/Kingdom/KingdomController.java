package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.Building.Barracks;
import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Exception.*;
import com.greenfox.tribes1.Progression.ProgressionService;
import com.greenfox.tribes1.Purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KingdomController {

  private KingdomService kingdomService;
  private ProgressionService progressionService;
  private PurchaseService purchaseService;
  private Long buildingUpgradeCost = 100L;
  private Long troopUpgradeCost = 10L;


  @Autowired
  public KingdomController(KingdomService kingdomService, ProgressionService progressionService, PurchaseService purchaseService) {
    this.kingdomService = kingdomService;
    this.progressionService = progressionService;
    this.purchaseService = purchaseService;
  }

  @GetMapping("/kingdom")
  public ResponseEntity showKingdom(Authentication authentication) throws NotValidKingdomNameException, TroopIdNotFoundException, BuildingNotValidException, NotValidTypeException, TroopNotValidException, BuildingIdNotFoundException {
    progressionService.checkConstruction();
    Kingdom kingdomByUser = kingdomService.getKindomFromAuth(authentication);
    return ResponseEntity.ok(kingdomService.createKingdomDTOFromKingdom(kingdomByUser));

  }

  @PutMapping("/kingdom")
  public ResponseEntity changeKingdomName(Kingdom kingdom, String newName) {
    return ResponseEntity.ok(kingdomService.createKingdomDTOFromKingdom(kingdom));
  }

  @GetMapping("/kingdom/buildings")
  public ResponseEntity showBuildings(Authentication authentication) {
    return ResponseEntity.ok(kingdomService.getKindomFromAuth(authentication).getBuildings());
  }

  @GetMapping("/kingdom/resources")
  public ResponseEntity showResources(Authentication authentication) {
    return ResponseEntity.ok(kingdomService.getKindomFromAuth(authentication).getResources());
  }

  @PostMapping("/kingdom/buildings")
  public ResponseEntity addBuilding(Authentication authentication, @RequestBody String type) throws NotValidResourceException, GoldNotEnoughException {
    Kingdom currentKingdom = kingdomService.getKindomFromAuth(authentication);
    purchaseService.purchase(currentKingdom, buildingUpgradeCost);
    progressionService.saveProgression(progressionService.createProgressionDTOForCreation(currentKingdom, type));
    return ResponseEntity.ok().build();
  }

  @PutMapping("/kingdom/buildings/{id}")
  public ResponseEntity upgradeBuilding(Authentication authentication, @PathVariable Long id) throws GoldNotEnoughException, NotValidResourceException, BuildingIdNotFoundException, UpgradeErrorException {
    Kingdom currentKingdom = kingdomService.getKindomFromAuth(authentication);
    purchaseService.purchaseBuildingUpgrade(currentKingdom, id);
    progressionService.saveProgression(progressionService.createProgressionDTOforBuildingUpgrade(currentKingdom, id));
    return ResponseEntity.ok().build();
  }

  @PostMapping("/kingdom/troop")
  public ResponseEntity addTroop(Authentication authentication, @RequestBody String type) throws NotValidResourceException, GoldNotEnoughException {
    Kingdom currentKingdom = kingdomService.getKindomFromAuth(authentication);
    if (isBarrackThere(currentKingdom.getBuildings())) {
      purchaseService.purchase(currentKingdom, troopUpgradeCost);
      progressionService.saveProgression(progressionService.createProgressionDTOForCreation(currentKingdom, type));
    }
    return ResponseEntity.ok().build();
  }

  @GetMapping("/kingdom/troops")
  public ResponseEntity showTroops(Authentication authentication) {
    return ResponseEntity.ok(kingdomService.getKindomFromAuth(authentication).getTroops());
  }

  private boolean isBarrackThere(List<Building> kingdomBuildings) {
    return kingdomBuildings.stream().anyMatch(b -> b instanceof Barracks);
  }
}