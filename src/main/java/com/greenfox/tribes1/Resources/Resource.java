package com.greenfox.tribes1.Resources;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resource_type")
@Getter
@Setter
public abstract class Resource implements Updatable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long amount;
  private Timestamp updated_at;
  private Long amountPerMinute;
  @Transient
  private Building building;

  @ManyToOne(
          fetch = FetchType.EAGER
  )
  @JoinTable(name = "kingdom_resources",
          joinColumns = @JoinColumn(name = "resources_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "kingdom_id", referencedColumnName = "id"))
  @JsonBackReference
  private Kingdom kingdom;

  Resource() {
    amount = 500L;
  }
}
