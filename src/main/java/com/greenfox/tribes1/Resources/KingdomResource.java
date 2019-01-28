package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public abstract class KingdomResource {
  
  private Long id;
  private Long amount;
  private Timestamp updated_at;
  
  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "kingdom_resources",
      joinColumns = @JoinColumn(name = "kingdom_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"))
  
  private Kingdom kingdom;
  
}
