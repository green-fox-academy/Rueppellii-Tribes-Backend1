package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserDTO;
import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserWithKingdomDTO;
import com.greenfox.tribes1.Exception.ErrorMsg;
import com.greenfox.tribes1.Exception.UserNotFoundException;
import com.greenfox.tribes1.Exception.UsernameTakenException;
import com.greenfox.tribes1.Exception.WrongPasswordException;
import com.greenfox.tribes1.Kingdom.Kingdom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    if (!isUsernameInDB(applicationUserDTO)) {
      ApplicationUser userToBeSaved = createUserFromDTO(applicationUserDTO);
      String kingdomName = applicationUserDTO.getKingdomName();
      
      if (isKingdomNameNullOrEmpty(kingdomName)) {
        userToBeSaved.setKingdom(new Kingdom(String.format("%s's kingdom", userToBeSaved.getUsername())));
      } else {
        userToBeSaved.setKingdom(new Kingdom(kingdomName));
      }
      userToBeSaved.getKingdom().setApplicationUser(userToBeSaved);
      return applicationUserRepository.save(userToBeSaved);
    }
    throw new UsernameTakenException("Username already taken, please choose an other one.");
  }
  
  public Boolean isUsernameInDB(ApplicationUserDTO applicationUserDTO) {
    return findByUsername(applicationUserDTO.getUsername()) != null;
  }
  
  public ApplicationUser createUserFromDTO(ApplicationUserDTO applicationUserDTO) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(applicationUserDTO, ApplicationUser.class);
  }
  
  public ApplicationUserWithKingdomDTO createDTOwithKingdomfromUser(ApplicationUser applicationUser) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(applicationUser, ApplicationUserWithKingdomDTO.class);
  }
  
  public ResponseEntity login(ApplicationUserDTO applicationUserDTO) throws UserNotFoundException, WrongPasswordException {
    if (isUsernameInDB(applicationUserDTO)) {
      if (isPasswordMatching(applicationUserDTO)) {
        return ResponseEntity.ok().body(new ErrorMsg("ok", "ok"));
      }
      throw new WrongPasswordException("Wrong password!");
    }
    throw new UserNotFoundException("No such user: " + applicationUserDTO.getUsername());
  }
  
  private Boolean isPasswordMatching(ApplicationUserDTO applicationUserDTO) {
    return applicationUserRepository
        .findByUsername(applicationUserDTO.getUsername())
        .map(applicationUser -> applicationUser.getPassword().equals(applicationUserDTO.getPassword())).orElse(false);
  }
  
  private Boolean isKingdomNameNullOrEmpty(String kingdomName) {
    return kingdomName == null || kingdomName.isEmpty();
  }
}
