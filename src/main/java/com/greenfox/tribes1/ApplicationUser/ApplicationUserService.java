package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserDTO;
import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserWithKingdomDTO;
import com.greenfox.tribes1.Building.BuildingRepository;
import com.greenfox.tribes1.Building.BuildingService;
import com.greenfox.tribes1.Exception.ErrorMsg;
import com.greenfox.tribes1.Exception.NotValidKingdomNameException;
import com.greenfox.tribes1.Exception.UsernameTakenException;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Kingdom.KingdomRepository;
import com.greenfox.tribes1.Kingdom.KingdomService;
import com.greenfox.tribes1.Resources.ResourceRepository;
import com.greenfox.tribes1.Resources.ResourceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {

  private ApplicationUserRepository applicationUserRepository;
  private BCryptPasswordEncoder encoder;
  private KingdomService kingdomService;

  @Autowired
  public ApplicationUserService(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder encoder, KingdomService kingdomService) {
    this.applicationUserRepository = applicationUserRepository;
    this.encoder = encoder;
    this.kingdomService = kingdomService;
  }

  public ApplicationUser findByUsername(String username) {
    return applicationUserRepository.findByUsername(username).orElse(null);
  }

  public ApplicationUser registerNewUser(ApplicationUserDTO applicationUserDTO) throws UsernameTakenException {
    String userName = applicationUserDTO.getUsername();

    if (!applicationUserRepository.existsByUsername(userName)) {
      String kingdomName = applicationUserDTO.getKingdomName();

      ApplicationUser userToBeSaved = createUserFromDTO(applicationUserDTO);
      userToBeSaved.setPassword(encoder.encode(applicationUserDTO.getPassword()));

      Kingdom kingdom = createKingdom(kingdomName, userName);
      kingdom.setApplicationUser(userToBeSaved);

      userToBeSaved.setKingdom(kingdom);

      kingdomService.setStarterBuildings(kingdom);
      kingdomService.setStarterResource(kingdom);

      return applicationUserRepository.save(userToBeSaved);
    }
    throw new UsernameTakenException("Username already taken, please choose an other one.");
  }

  private Kingdom createKingdom(String kingdomName, String username) {
    if (isKingdomNameNullOrEmpty(kingdomName)) {
      return new Kingdom(String.format("%s's kingdom", username));
    }
    return new Kingdom(kingdomName);
  }

  public ApplicationUser createUserFromDTO(ApplicationUserDTO applicationUserDTO) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(applicationUserDTO, ApplicationUser.class);
  }

  public ApplicationUserWithKingdomDTO createDTOwithKingdomfromUser(ApplicationUser applicationUser) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(applicationUser, ApplicationUserWithKingdomDTO.class);
  }

  public ResponseEntity login(ApplicationUserDTO applicationUserDTO) {
    if (applicationUserRepository.existsByUsername(applicationUserDTO.getUsername())) {
      if (isPasswordMatching(applicationUserDTO)) {
        return ResponseEntity.ok().body(new ErrorMsg("ok", "ok"));
      }
    }
    throw new UsernameNotFoundException("No such user: " + applicationUserDTO.getUsername());
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
