package com.greenfox.tribes1.Troop.Model;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
public abstract class Troop {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @PositiveOrZero
  private Long level;
  private Long HP;
  private Long attack;
  private Long defense;
  private Timestamp started_at;
  private Timestamp finished_at;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "kingdom_troops",
          joinColumns = @JoinColumn(name = "troops_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "kingdom_id", referencedColumnName = "id"))
  private Kingdom kingdom;
}
