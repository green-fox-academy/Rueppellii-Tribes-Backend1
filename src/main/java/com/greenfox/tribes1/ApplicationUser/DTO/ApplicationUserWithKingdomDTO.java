package com.greenfox.tribes1.ApplicationUser.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUserWithKingdomDTO {
  private Long id;
  private String username;
  private Long kingdomId;
}
