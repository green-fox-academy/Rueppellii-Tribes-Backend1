package com.greenfox.tribes1.kingdomelement.Resources;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.kingdomelement.KingdomElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resource_type")
@AllArgsConstructor
@Getter
@Setter
public abstract class Resource extends KingdomElement implements Updatable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long amount;
  private Timestamp updated_at;
  private Long amountPerMinute;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id", referencedColumnName = "id")
  @JsonBackReference
  private Kingdom kingdom;

  Resource() {
    updated_at = new Timestamp(System.currentTimeMillis());
    amount = 500L;
  }

  @Override
  @SneakyThrows
  public void update(Long difference) {
    amount += amountPerMinute * difference;
    updated_at = new Timestamp(System.currentTimeMillis());
  }
}