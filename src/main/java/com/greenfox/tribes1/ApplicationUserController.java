package com.greenfox.tribes1;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationUserController {
    @Autowired
    ApplicationUserService applicationUserService;

    public ApplicationUserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ApplicationUserDTO applicationUserDTO) {
        String username = applicationUserDTO.getUsername();

        if (username != null) {
            if (applicationUserService.findByUsername(username) != null) {
                return ResponseEntity.status(409).body(new ErrorMsg("Username already taken, please choose an other one."));
            } else {
                ModelMapper modelMapper = new ModelMapper();
                ApplicationUser applicationUser = modelMapper.map(applicationUserDTO, ApplicationUser.class);
                if (applicationUser.getKingdom() == null) {
                    applicationUser.setKingdom(String.format("%s's kingdom", username));
                }
                return ResponseEntity.ok().body(applicationUser);
            }
        }
        return ResponseEntity.status(400).body(new ErrorMsg("Missing parameter(s): username!"));
    }
}
