package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Resources.KingdomResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
//    List<Building> buildings;
//    List<Troop> troops;
//    Location location;
  
  @OneToOne(mappedBy = "kingdom")
  ApplicationUser applicationUser;
  
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