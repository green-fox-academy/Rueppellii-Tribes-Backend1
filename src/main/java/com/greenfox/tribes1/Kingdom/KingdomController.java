package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomController {
  private KingdomService kingdomService;

  public KingdomController(KingdomService kingdomService) {
    this.kingdomService = kingdomService;
  }

  @GetMapping(value = "/kingdom", produces = {MediaType.APPLICATION_JSON_VALUE} )
  public ResponseEntity kingdomDTO(@RequestBody ApplicationUser applicationUser) {
    Kingdom kingdomByUser = kingdomService.findByApplicationUser(applicationUser);
    return ResponseEntity.ok()
            .header("Content-Type", "application/json; charset=utf-8")
            .body(kingdomService.createKingdomDTOFromKingdom(kingdomByUser));
  }

  @GetMapping("/kingdomA")
  public ResponseEntity kingdomDTOA(Authentication authentication) {
    ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
    Kingdom kingdomByUser = kingdomService.findByApplicationUser(applicationUser);
    return ResponseEntity.ok(kingdomService.createKingdomDTOFromKingdom(kingdomByUser));
  }

  @GetMapping("/kingdomlist")
  public ResponseEntity kingdomList() {
    return ResponseEntity.ok(kingdomService.findAll());
  }
}
