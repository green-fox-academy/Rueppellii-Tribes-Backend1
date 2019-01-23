package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.Exception.UsernameTakenException;
import org.modelmapper.ModelMapper;
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

  public ApplicationUser saveUserIfValid(ApplicationUserDTO applicationUserDTO) throws UsernameTakenException {
    ModelMapper modelMapper = new ModelMapper();
    ApplicationUser applicationUser = modelMapper.map(applicationUserDTO, ApplicationUser.class);

    if (isUsernameInDB(applicationUserDTO)) {
      throw new UsernameTakenException("Username already taken, please choose an other one.");
    }

    return applicationUserRepository.save(applicationUser);
  }

  public Boolean isUsernameInDB(ApplicationUserDTO applicationUserDTO) {
    return applicationUserRepository.findByUsername(applicationUserDTO.getUsername()).isPresent();
  }

}
