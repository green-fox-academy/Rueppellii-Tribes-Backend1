package com.greenfox.tribes1.Resources;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resource_type")
@AllArgsConstructor
@Getter
@Setter
public abstract class KingdomResource implements Updatable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long amount;
  private Timestamp updated_at;
  private Long amountPerMinute;
  @Transient
  private Building building;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id", referencedColumnName = "id")
  @JsonBackReference
  private Kingdom kingdom;

  KingdomResource() {
    amount = 500L;
  }
}
