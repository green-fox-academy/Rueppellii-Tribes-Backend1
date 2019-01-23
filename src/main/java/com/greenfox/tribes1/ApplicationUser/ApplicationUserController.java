package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.Exception.UserNotFoundException;
import com.greenfox.tribes1.Exception.UsernameTakenException;
import com.greenfox.tribes1.Exception.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ApplicationUserController {
  @Autowired
  ApplicationUserService applicationUserService;

  public ApplicationUserController(ApplicationUserService applicationUserService) {
    this.applicationUserService = applicationUserService;
  }

  @PostMapping("/register")
  public void register(@Valid @RequestBody ApplicationUserDTO applicationUserDTO) throws UsernameTakenException {
    String username = applicationUserDTO.getUsername();
    if (applicationUserService.findByUsername(username) != null) {
      throw new UsernameTakenException("Username already taken, please choose an other one.");
 /*     } else {

        if (applicationUser.getKingdom() == null) {
          applicationUser.setKingdom(String.format("%s's kingdom", username));
        }
        return ResponseEntity.ok().body(applicationUser);
      }*/
    }
  }

  @RequestMapping("/login")
  public ResponseEntity login(@Valid @RequestBody ApplicationUserDTO applicationUserDTO) throws WrongPasswordException, UserNotFoundException {
    String username = applicationUserDTO.getUsername();

    ApplicationUser userToFind = applicationUserService.findByUsername(username);

    if (userToFind != null) {
      return ResponseEntity.ok().build();
    } else if (!userToFind.getPassword().equals(applicationUserDTO.getPassword())) {
      throw new WrongPasswordException("Wrong password");
    }
    throw new UserNotFoundException("No such user: " + applicationUserDTO.getUsername());

  }
}
