package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.Exception.UserNotFoundException;
import com.greenfox.tribes1.Exception.UsernameTakenException;
import com.greenfox.tribes1.Exception.WrongPasswordException;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Kingdom.KingdomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {
  
  private ApplicationUserRepository applicationUserRepository;
  private KingdomRepository kingdomRepository;
  
  @Autowired
  public ApplicationUserService(ApplicationUserRepository applicationUserRepository, KingdomRepository kingdomRepository) {
    this.applicationUserRepository = applicationUserRepository;
    this.kingdomRepository = kingdomRepository;
  }
  
  public ApplicationUser findByUsername(String username) {
    return applicationUserRepository.findByUsername(username).orElse(null);
  }
  
  public ApplicationUser saveUserIfValid(ApplicationUserDTO applicationUserDTO) throws UsernameTakenException {
    if (!isUsernameInDB(applicationUserDTO)) {
      ApplicationUser userToBeSaved = createUserFromDTO(applicationUserDTO);
      String kingdomName = applicationUserDTO.getKingdomName();
      
      if (kingdomName == null || kingdomName.isEmpty()) {
        userToBeSaved.setKingdom(new Kingdom(String.format("%s's kingdom", userToBeSaved.getUsername())));
      } else {
        userToBeSaved.setKingdom(new Kingdom(kingdomName));
      }
      kingdomRepository.save(userToBeSaved.getKingdom());
      return applicationUserRepository.save(userToBeSaved);
    }
    throw new UsernameTakenException("Username already taken, please choose an other one.");
  }
  
  public Boolean isUsernameInDB(ApplicationUserDTO applicationUserDTO) {
    return applicationUserRepository.findByUsername(applicationUserDTO.getUsername()).orElse(null) != null;
  }
  
  public ApplicationUser createUserFromDTO(ApplicationUserDTO applicationUserDTO) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(applicationUserDTO, ApplicationUser.class);
  }
  
  public ResponseEntity login(ApplicationUserDTO applicationUserDTO) throws UserNotFoundException, WrongPasswordException {
    if (isUsernameInDB(applicationUserDTO)) {
      if (isPasswordMatching(applicationUserDTO)) {
        return ResponseEntity.ok().build();
      }
      throw new WrongPasswordException("Wrong password!");
    }
    throw new UserNotFoundException("No such user: " + applicationUserDTO.getUsername());
  }
  
  private Boolean isPasswordMatching(ApplicationUserDTO applicationUserDTO) {
    return applicationUserRepository
        .findByUsername(applicationUserDTO.getUsername())
        .get()
        .getPassword()
        .equals(applicationUserDTO.getPassword());
  }
}
