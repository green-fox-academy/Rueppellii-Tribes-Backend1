package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomController {
  
  private KingdomService kingdomService;
  
  @Autowired
  public KingdomController(KingdomService kingdomService) {
    this.kingdomService = kingdomService;
  }
  
  @GetMapping(value = "/kingdom")
  public ResponseEntity kingdomDTO(@RequestBody ApplicationUser applicationUser) {
    Kingdom kingdomByUser = kingdomService.findKingdomByApplicationUser(applicationUser);
    return ResponseEntity.ok()
        .body(kingdomService.createKingdomDTOFromKingdom(kingdomByUser));
  }
  
  @GetMapping("/kingdom/resources")
  public ResponseEntity kingdomResourcesDTO(@RequestBody ApplicationUser applicationUser) {
    Kingdom kingdomByUser = kingdomService.findKingdomByApplicationUser(applicationUser);
    return ResponseEntity.ok()
        .body(kingdomService.createKingdomResourceDTOFromKingdom(kingdomByUser));
  }
  
  @PutMapping("/kingdom")
  public ResponseEntity KingdomNameChange(Kingdom kingdom, String newName) {
    Kingdom kingdomWithNewMName = kingdomService.renameKingdom(kingdom, newName);
    return ResponseEntity.ok()
        .body(kingdomService.createKingdomDTOFromKingdom(kingdomWithNewMName));
  }
  
  @GetMapping("/kingdomA")
  public ResponseEntity kingdomDTOA(Authentication authentication) {
    ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
    Kingdom kingdomByUser = kingdomService.findKingdomByApplicationUser(applicationUser);
    return ResponseEntity.ok(kingdomService.createKingdomDTOFromKingdom(kingdomByUser));
  }
  
  @GetMapping("/kingdomlist")
  public ResponseEntity kingdomList() {
    return ResponseEntity.ok(kingdomService.findAll());
  }
  
}