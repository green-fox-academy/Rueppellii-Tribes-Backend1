package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
public abstract class KingdomResource {
  
  private Long amount;
  private Timestamp updated_at;
  @ManyToOne
  private Kingdom kingdom;
  
  KingdomResource() {
    amount = 500L;
  }
}
