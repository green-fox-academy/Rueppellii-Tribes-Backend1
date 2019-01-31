package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
class Food extends KingdomResource {
  
  public Food(Long id, Long amount, Timestamp updated_at, Kingdom kingdom) {
    super(id, amount, updated_at, kingdom);
  }
}
