package com.greenfox.tribes1.ApplicationUser.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationUserDTO {
  @NotBlank
  private String username;
  @NotBlank
  private String password;
  private String kingdomName;

}
