package com.greenfox.tribes1.Security;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;

import java.util.Optional;

public interface UserService {
    public Optional<ApplicationUser> getByUsername(String username);
}