package com.greenfox.tribes1.kingdomelement.Building;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.kingdomelement.KingdomElement;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "building_type")
@Getter
@Setter
public abstract class Building extends KingdomElement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long level;
  private Float HP;
  private Timestamp started_at;
  private Timestamp finished_at;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id", referencedColumnName = "id")
  @JsonBackReference
  private Kingdom kingdom;

  public void increaseLevel() {
    setLevel(getLevel() + 1L);
    setHP(getHP() * 1.1F);
  }

  abstract void buildingUpgrade();

}
