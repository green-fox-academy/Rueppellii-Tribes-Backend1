package com.greenfox.tribes1.Kingdom;

import lombok.*;
import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Troop.Model.Troop;
import com.greenfox.tribes1.Building.Building;
import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Kingdom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
//    private Long userId;
//    List<Resource> resources;
//    Location location;

  @OneToOne(mappedBy = "kingdom")
  ApplicationUser applicationUser;

  @OneToMany
  private List<Troop> troops;

  List<Building> buildings;

  public Kingdom(String name) {
    this.name = name;
  }
}
