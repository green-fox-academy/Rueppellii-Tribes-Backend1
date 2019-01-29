package com.greenfox.tribes1.Kingdom.DTO;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KingdomDTO {
  private Long id;
  private String kingdomName;
  private String applicationUserName;
//  private ApplicationUser applicationUser;
//    private Long userId;
//    List<Building> buildings;
//    List<Resource> resources;
//    List<Troop> troops;
//    Location location;
}
