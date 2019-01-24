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
  ApplicationUserService applicationUserService;

  @Autowired
  public ApplicationUserController(ApplicationUserService applicationUserService) {
    this.applicationUserService = applicationUserService;
  }

  @PostMapping("/register")
  public void register(@Valid @RequestBody ApplicationUserDTO applicationUserDTO) throws UsernameTakenException {
    applicationUserService.saveUserIfValid(applicationUserDTO);
  }

  @RequestMapping("/login")
  public ResponseEntity login(@Valid @RequestBody ApplicationUserDTO applicationUserDTO) throws WrongPasswordException, UserNotFoundException {
    return applicationUserService.login(applicationUserDTO);
  }
}
