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
  private BuildingService buildingService;
  private ResourceService resourceService;
  private KingdomRepository kingdomRepository;
  private BuildingRepository buildingRepository;
  private BCryptPasswordEncoder encoder;
  private ResourceRepository resourceRepository;

  @Autowired
  public ApplicationUserService(ApplicationUserRepository applicationUserRepository, BuildingService buildingService, ResourceService resourceService, KingdomRepository kingdomRepository, BuildingRepository buildingRepository, BCryptPasswordEncoder encoder, ResourceRepository resourceRepository) {
    this.applicationUserRepository = applicationUserRepository;
    this.buildingService = buildingService;
    this.resourceService = resourceService;
    this.kingdomRepository = kingdomRepository;
    this.buildingRepository = buildingRepository;
    this.encoder = encoder;
    this.resourceRepository = resourceRepository;
  }

  public ApplicationUser findByUsername(String username) {
    return applicationUserRepository.findByUsername(username).orElse(null);
  }

  public ApplicationUser registerNewUser(ApplicationUserDTO applicationUserDTO) throws UsernameTakenException, NotValidKingdomNameException {
    if (!applicationUserRepository.existsByUsername(applicationUserDTO.getUsername())) {
      ApplicationUser userToBeSaved = createUserFromDTO(applicationUserDTO);
      userToBeSaved.setPassword(encoder.encode(applicationUserDTO.getPassword()));
      String kingdomName = applicationUserDTO.getKingdomName();
      Kingdom myKingdom;
      if (isKingdomNameNullOrEmpty(kingdomName)) {
        myKingdom = new Kingdom(String.format("%s's kingdom", userToBeSaved.getUsername()));
      } else {
        myKingdom = new Kingdom(kingdomName);
      }

      userToBeSaved.setKingdom(myKingdom);
      myKingdom.setApplicationUser(userToBeSaved);

      buildingService.setStarterBuildings(userToBeSaved.getKingdom());
      resourceService.setStarterResource(userToBeSaved.getKingdom());
      ApplicationUser applicationUser = applicationUserRepository.save(userToBeSaved);
      System.out.println(buildingRepository.findById(1L).get().getKingdom());
      System.out.println(resourceRepository.findById(1L).get().getKingdom());
      System.out.println(kingdomRepository.findById(1L).get().getBuildings());
      System.out.println(kingdomRepository.findById(1L).get().getResources());

      return applicationUser;
    }
    throw new UsernameTakenException("Username already taken, please choose an other one.");
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
