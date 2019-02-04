package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public abstract class KingdomResource {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long amount;
  private Timestamp updated_at;
  //private Long amountPerMinute;
   @ManyToOne(
      cascade = CascadeType.PERSIST,
      fetch = FetchType.EAGER
  )
  @JoinTable(name = "kingdom_resources",
      joinColumns = @JoinColumn(name = "kingdom_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "resources_id", referencedColumnName = "id"))
  private Kingdom kingdom;
  
  KingdomResource() {
    amount = 500L;
  }
}
