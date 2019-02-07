package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserDTO;
import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserWithKingdomDTO;
import com.greenfox.tribes1.Exception.UsernameTakenException;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Security.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserService implements UserService {

  private ApplicationUserRepository applicationUserRepository;
  private BCryptPasswordEncoder encoder;

  @Autowired
  public ApplicationUserService(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder encoder) {
    this.applicationUserRepository = applicationUserRepository;
    this.encoder = encoder;
  }

  @Override
  public Optional<ApplicationUser> getByUsername(String username) {
    return this.applicationUserRepository.findByUsername(username);
  }

  public ApplicationUser registerNewUser(ApplicationUserDTO applicationUserDTO) throws UsernameTakenException {
    if (!applicationUserRepository.existsByUsername(applicationUserDTO.getUsername())) {
      ApplicationUser userToBeSaved = createUserFromDTO(applicationUserDTO);
      userToBeSaved.setPassword(encoder.encode(applicationUserDTO.getPassword()));
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

  public ApplicationUser createUserFromDTO(ApplicationUserDTO applicationUserDTO) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(applicationUserDTO, ApplicationUser.class);
  }

  public ApplicationUserWithKingdomDTO createDTOwithKingdomfromUser(ApplicationUser applicationUser) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(applicationUser, ApplicationUserWithKingdomDTO.class);
  }


  public ApplicationUserDTO login(ApplicationUserDTO applicationUserDTO) {
    if (applicationUserRepository.existsByUsername(applicationUserDTO.getUsername())) {
      if (isPasswordMatching(applicationUserDTO)) {
        return applicationUserDTO;
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

  public Long getIdFromDB(String username) {
    Optional<ApplicationUser> applicationUser = applicationUserRepository.findByUsername(username);
    return applicationUser.get().getId();

  }

}
