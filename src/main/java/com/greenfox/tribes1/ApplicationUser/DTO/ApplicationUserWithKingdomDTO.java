package com.greenfox.tribes1.ApplicationUser.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationUserWithKingdomDTO {
  private Long id;
  private String username;
  private Long kingdomId;
}
