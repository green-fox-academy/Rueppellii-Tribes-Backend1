package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Security.Model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomController {
  private KingdomService kingdomService;

  @Autowired
  public KingdomController(KingdomService kingdomService) {
    this.kingdomService = kingdomService;
  }

  @GetMapping(value = "/kingdomUser")
  public ResponseEntity kingdomDTO(@RequestBody ApplicationUser applicationUser) {
    Kingdom kingdomByUser = kingdomService.findKingdomByApplicationUser(applicationUser);
    return ResponseEntity.ok()
            .body(kingdomService.createKingdomDTOFromKingdom(kingdomByUser));
  }

  @GetMapping("/kingdom")
  public ResponseEntity kingdomDTOA(Authentication authentication) {
    UserContext userContext = (UserContext) authentication.getPrincipal();
    System.out.println(userContext.getUsername());
    Kingdom kingdomByUsername = kingdomService.findKingdomByApplicationUserName(userContext.getUsername());

    return ResponseEntity.ok(kingdomService.createKingdomDTOFromKingdom(kingdomByUsername));
   // return  ResponseEntity.ok().build();
  }

  @GetMapping("/kingdomlist")
  public ResponseEntity kingdomList() {
    return ResponseEntity.ok(kingdomService.findAll());
  }

  @GetMapping("kingdom/buildings")
  private ResponseEntity show_buildings(Authentication authentication) {
    ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
    Kingdom kingdomByUser = kingdomService.findKingdomByApplicationUser(applicationUser);
    return ResponseEntity.ok(kingdomByUser.getBuildings());
  }
}
