package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.Security.Model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomController {

  private KingdomService kingdomService;

  @Autowired
  public KingdomController(KingdomService kingdomService) {
    this.kingdomService = kingdomService;
  }

  @GetMapping("/kingdom")
  public ResponseEntity show_kingdom(Authentication authentication) {
    UserContext userContext = (UserContext) authentication.getPrincipal();
    Kingdom kingdomByUsername = kingdomService.findKingdomByApplicationUserName(userContext.getUsername());
    return ResponseEntity.ok(kingdomService.createKingdomDTOFromKingdom(kingdomByUsername));
  }

  @PutMapping("/kingdom")
  public ResponseEntity change_kingdomName(Kingdom kingdom, String newName) {
    return ResponseEntity.ok(kingdomService.createKingdomDTOFromKingdom(kingdom));
  }

  @GetMapping("kingdom/buildings")
  public ResponseEntity show_buildings(Authentication authentication) {
    UserContext userContext = (UserContext) authentication.getPrincipal();
    Kingdom kingdomByUser = kingdomService.findKingdomByApplicationUserName(userContext.getUsername());
    return ResponseEntity.ok(kingdomByUser.getBuildings());
  }

  @GetMapping("/kingdom/resources")
  public ResponseEntity show_resources(Authentication authentication) {
    UserContext userContext = (UserContext) authentication.getPrincipal();
    Kingdom kingdomByUser = kingdomService.findKingdomByApplicationUserName(userContext.getUsername());
    return ResponseEntity.ok(kingdomByUser.getResources());
  }
}