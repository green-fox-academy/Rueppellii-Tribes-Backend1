package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.Progression.DTO.ProgressionDTO;
import com.greenfox.tribes1.Progression.ProgressionService;
import com.greenfox.tribes1.Security.Model.UserContext;
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
  public ResponseEntity show_kingdom(Authentication authentication) {
    return ResponseEntity.ok(kingdomService.getKindomFromAuht(authentication));
  }

  @PutMapping("/kingdom")
  public ResponseEntity change_kingdomName(Kingdom kingdom, String newName) {
    return ResponseEntity.ok(kingdomService.createKingdomDTOFromKingdom(kingdom));
  }

  @GetMapping("/kingdom/buildings")
  public ResponseEntity show_buildings(Authentication authentication) {
    return ResponseEntity.ok(kingdomService.getKindomFromAuht(authentication).getBuildings());
  }

  @GetMapping("/kingdom/resources")
  public ResponseEntity show_resources(Authentication authentication) {
    return ResponseEntity.ok(kingdomService.getKindomFromAuht(authentication).getResources());
  }

  @PostMapping("/kingdom/buildings")
  public ResponseEntity addBuilding(Authentication authentication, @RequestBody String type) {
    ProgressionDTO progressionDTO = new ProgressionDTO();
    progressionDTO.setType(type);
    progressionDTO.setKingdom(kingdomService.getKindomFromAuht(authentication));
    progressionDTO.setLevel(0L);
    progressionDTO.setModel_id(0L);
    progressionService.saveProgression(progressionDTO);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/kingdom/troops")
  public ResponseEntity show_troops(Authentication authentication) {
    UserContext userContext = (UserContext) authentication.getPrincipal();
    Kingdom kingdomByUser = kingdomService.findKingdomByApplicationUserName(userContext.getUsername());
    return ResponseEntity.ok(kingdomByUser.getTroops());
  }
}