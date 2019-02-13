package com.greenfox.tribes1.Kingdom;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
  @JsonManagedReference
  private List<KingdomResource> resources;

  @OneToOne(mappedBy = "kingdom")
  ApplicationUser applicationUser;

  @OneToMany(mappedBy = "kingdom", cascade = CascadeType.PERSIST)
  @JsonManagedReference
  private List<Troop> troops;

  @OneToMany(mappedBy = "kingdom", cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
  @JsonManagedReference
  private List<Building> buildings;

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

  public List<KingdomResource> getResources() {
    return resources;
  }
}