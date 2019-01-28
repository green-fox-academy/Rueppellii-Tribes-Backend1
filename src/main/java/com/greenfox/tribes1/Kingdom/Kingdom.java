package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Resources.KingdomResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Kingdom {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
//    private Long userId;
//    List<Building> buildings;
//    List<Resource> resources;
//    List<Troop> troops;
//    Location location;
  
  @OneToOne(mappedBy = "kingdom")
  ApplicationUser applicationUser;
  
  @OneToOne(mappedBy = "kingdom")
  KingdomResource resources;
  
  public Kingdom(String name) {
    this.name = name;
  }
}
