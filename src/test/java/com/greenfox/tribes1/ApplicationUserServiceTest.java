package com.greenfox.tribes1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationUserServiceTest {
    ApplicationUserRepository applicationUserRepository;
    ApplicationUserService applicationUserService;

    @Test
    public void saveUserIfValidReturnsNullIfUserAlreadyExist() {
        ApplicationUser testUser = new ApplicationUser("John", "password", "john@john.com");
        applicationUserService.saveUser(testUser);
        assertNull(applicationUserService.saveUserIfValid(new ApplicationUser("John", "password", "john@john.com")));
    }
}