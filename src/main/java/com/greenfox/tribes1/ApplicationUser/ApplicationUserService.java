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
    ApplicationUser userToBeSaved = createUserFromDTO(applicationUserDTO);

    if (!isUsernameInDB(userToBeSaved)) {
      return applicationUserRepository.save(userToBeSaved);
    }
    throw new UsernameTakenException("Username already taken, please choose an other one.");
  }

  public Boolean isUsernameInDB(ApplicationUser applicationUserDTO) {
    return applicationUserRepository.findByUsername(applicationUserDTO.getUsername()) != null;
  }

  public ApplicationUser createUserFromDTO(ApplicationUserDTO applicationUserDTO) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(applicationUserDTO, ApplicationUser.class);
  }
}
