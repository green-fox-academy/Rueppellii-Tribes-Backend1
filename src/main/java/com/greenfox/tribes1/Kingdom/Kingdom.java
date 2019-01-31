package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import lombok.*;
import com.greenfox.tribes1.Building.Building;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Kingdom {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String name;
//    private Long userId;
//    List<Resource> resources;
//    List<Troop> troops;
//    Location location;

  @OneToOne(mappedBy = "kingdom")
  ApplicationUser applicationUser;

  @OneToMany
  List<Building> buildings;

  public Kingdom(String name) {
    this.name = name;
  }
}
