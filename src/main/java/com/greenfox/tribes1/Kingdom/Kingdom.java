package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import lombok.*;
import javax.persistence.*;

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
//    List<Building> buildings;
//    List<Resource> resources;
//    List<Troop> troops;
//    Location location;

  @OneToOne(mappedBy = "kingdom")
  ApplicationUser applicationUser;

  public Kingdom(String name) {
    this.name = name;
  }
}
