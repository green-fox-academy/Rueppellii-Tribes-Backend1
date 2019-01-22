package com.greenfox.tribes1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {

    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public ApplicationUser findByUsername(String username) {
        return applicationUserRepository.findByUsername(username);
    }

    public ApplicationUser saveUserIfValid(ApplicationUser applicationUser) {
        if ((applicationUserRepository.findByUsername(applicationUser.getUsername()) == null) &&
            !applicationUser.getUsername().isEmpty() && !applicationUser.getPassword().isEmpty()){
            saveUser(applicationUser);
            return applicationUser;
        }
        return null;
    }

    public void saveUser(ApplicationUser applicationUser) {
            applicationUserRepository.save(applicationUser);
    }


}
