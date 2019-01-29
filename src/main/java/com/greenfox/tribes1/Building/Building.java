package com.greenfox.tribes1.Building;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public abstract class Building {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long level;
  private Long HP;
  private Long started_at;
  private Long finished_at;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name="kingdom_buildings",
          joinColumns = @JoinColumn(name ="buildings_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "kingdom_id", referencedColumnName = "id"))
  private Kingdom kingdom;

}
