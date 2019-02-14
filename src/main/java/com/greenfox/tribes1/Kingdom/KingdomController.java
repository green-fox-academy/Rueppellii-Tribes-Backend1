package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.Exception.*;
import com.greenfox.tribes1.Progression.DTO.ProgressionDTO;
import com.greenfox.tribes1.Progression.ProgressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class KingdomController {

  private KingdomService kingdomService;

  private ProgressionService progressionService;

  @Autowired
  public KingdomController(KingdomService kingdomService, ProgressionService progressionService) {
    this.kingdomService = kingdomService;
    this.progressionService = progressionService;
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
  public ResponseEntity addBuilding(Authentication authentication, @RequestBody String type) {
    ProgressionDTO progressionDTO = new ProgressionDTO();
    progressionDTO.setType(type);
    progressionDTO.setKingdom(kingdomService.getKindomFromAuth(authentication));
    progressionDTO.setLevel(0L);
    progressionDTO.setModel_id(0L);
    progressionService.saveProgression(progressionDTO);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/kingdom/troops")
  public ResponseEntity showTroops(Authentication authentication) {
    return ResponseEntity.ok(kingdomService.getKindomFromAuth(authentication).getTroops());
  }
}