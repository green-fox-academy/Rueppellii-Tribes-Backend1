package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
//    private Long userId;
//    List<Building> buildings;
//    List<Resource> resources;
//    List<Troop> troops;
//    Location location;

 @OneToOne
 ApplicationUser applicationUser;

  public Kingdom(String name) {
    this.name = name;
  }
}
