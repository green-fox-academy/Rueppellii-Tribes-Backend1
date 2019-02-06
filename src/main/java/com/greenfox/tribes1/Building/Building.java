package com.greenfox.tribes1.Building;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "building_type")
@Getter
@Setter
public abstract class Building {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long level;
  private Long HP;
  private Timestamp started_at;
  private Timestamp finished_at;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "kingdom_buildings",
          inverseJoinColumns = @JoinColumn(name = "kingdom_id", referencedColumnName = "id"),
          joinColumns = @JoinColumn(name = "buildings_id", referencedColumnName = "id"))

  private Kingdom kingdom;

}
