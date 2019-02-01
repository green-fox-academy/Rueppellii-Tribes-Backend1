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

  @GetMapping(value = "/kingdom")
  public ResponseEntity kingdomDTO(@RequestBody ApplicationUser applicationUser) {
    Kingdom kingdomByUser = kingdomService.findByApplicationUser(applicationUser);
    return ResponseEntity.ok()
            .body(kingdomService.createKingdomDTOFromKingdom(kingdomByUser));
  }

  @GetMapping("/kingdomA")
  public ResponseEntity kingdomDTOA(Authentication authentication) {
    System.out.println(authentication.getCredentials());
    System.out.println(authentication.getAuthorities());
    System.out.println(authentication.getPrincipal());
    System.out.println(authentication.getDetails());
    System.out.println(authentication.getName());
    UserContext userContext = (UserContext) authentication.getPrincipal();
    System.out.println(userContext.getUsername());

   // Kingdom kingdomByUser = kingdomService.findByApplicationUser(applicationUser);
    //return ResponseEntity.ok(kingdomService.createKingdomDTOFromKingdom(kingdomByUser));
    return ResponseEntity.ok().build();
  }

  @GetMapping("/kingdomlist")
  public ResponseEntity kingdomList() {
    return ResponseEntity.ok(kingdomService.findAll());
  }
}
