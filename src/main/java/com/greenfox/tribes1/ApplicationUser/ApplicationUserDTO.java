package com.greenfox.tribes1.ApplicationUser;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationUserDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String kingdomName;

    public ApplicationUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
