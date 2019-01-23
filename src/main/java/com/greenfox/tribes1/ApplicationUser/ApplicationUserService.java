package com.greenfox.tribes1.ApplicationUser;

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
    return applicationUserRepository.findByUsername(username).orElse(null);
  }

  //atirni - Krisz & Sol
  public ApplicationUser saveUserIfValid(ApplicationUser applicationUser) {
    if (findByUsername(applicationUser.getUsername()) != null ||
            applicationUser.getUsername().isEmpty() ||
            applicationUser.getPassword().isEmpty()) {
      return null;
    }
    applicationUserRepository.save(applicationUser);
    return applicationUser;

  }

}
