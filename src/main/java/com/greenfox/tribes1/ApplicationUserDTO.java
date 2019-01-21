package com.greenfox.tribes1;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationUserDTO {
    @NotBlank
    private String username;
    private String password;
    private String kingdom;

    public ApplicationUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
