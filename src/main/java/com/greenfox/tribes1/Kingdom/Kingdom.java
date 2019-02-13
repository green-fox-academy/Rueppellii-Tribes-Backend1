package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Resources.KingdomResource;
import com.greenfox.tribes1.Troop.Model.Troop;
import lombok.*;

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
  @OneToMany(mappedBy = "kingdom", cascade = CascadeType.PERSIST)
  List<KingdomResource> resources;

  @OneToOne(mappedBy = "kingdom")
  ApplicationUser applicationUser;

  @OneToMany
  private List<Troop> troops;

  @OneToMany(mappedBy = "kingdom", cascade = CascadeType.PERSIST)
  List<Building> buildings;

  public Kingdom(String name) {
    this.name = name;
  }
}