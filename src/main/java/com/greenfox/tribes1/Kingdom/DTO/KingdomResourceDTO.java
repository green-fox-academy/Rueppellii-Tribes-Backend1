package com.greenfox.tribes1.Kingdom.DTO;

import com.greenfox.tribes1.Resources.KingdomResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KingdomResourceDTO {
  
  private Long id;
  private String kingdomName;
  private String applicationUserName;
  private KingdomResource resources;
}
