package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Troop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long HP;
  private Long attack;
  private Long defense;
  private Long started_at;
  private Long finished_at;

  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name="kingdom_troops",
          joinColumns = @JoinColumn(name ="troop_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "kingdom_id", referencedColumnName = "id"))
  private Kingdom kingdom;
}
