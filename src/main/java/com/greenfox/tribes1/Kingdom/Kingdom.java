package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Resources.KingdomResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
public class Kingdom {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @OneToMany
  private List<KingdomResource> resources = new ArrayList<>();

//    private Long userId;
//    Location location;
  
  @OneToOne(mappedBy = "kingdom")
  ApplicationUser applicationUser;
  
  @OneToMany
  List<Building> buildings;
  
  public Kingdom(String name) {
    //resourceCreator();
    //troopCreator();
    //buildingCreator();
    this.name = name;
  }
  
  public Kingdom() {
    //resourceCreator();
    //troopCreator();
    //buildingCreator();
  }
  
  private void resourceCreator() {
    /*ResourceFactory resourceFactory = new ResourceFactory();
    resources.add(resourceFactory.getResource("food"));
    resources.add(resourceFactory.getResource("gold"));*/
  }
  
  public List<KingdomResource> getResources() {
    return resources;
  }
}