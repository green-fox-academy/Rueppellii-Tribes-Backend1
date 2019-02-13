package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Resources.KingdomResource;
import com.greenfox.tribes1.Troop.Model.Troop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
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

  @OneToOne(mappedBy = "kingdom", cascade = CascadeType.PERSIST)
  ApplicationUser applicationUser;

  @OneToMany(mappedBy = "kingdom", cascade = CascadeType.PERSIST)
  private List<Troop> troops;

  @OneToMany(mappedBy = "kingdom", cascade = CascadeType.PERSIST)
  List<Building> buildings;

  public Kingdom(String name) {
    this.name = name;
  }

  public Kingdom() {
    resourceCreator();
  }

  private void resourceCreator() {
    /*ResourceFactory resourceFactory = new ResourceFactory();
    resources.add(resourceFactory.getResource(ResourceType.food));
    resources.add(resourceFactory.getResource(ResourceType.gold));*/
  }

  protected List<KingdomResource> getResources() {
    return resources;
  }
}