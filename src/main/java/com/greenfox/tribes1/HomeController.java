package com.greenfox.tribes1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  
  @RequestMapping("/")
  public String setup() {
    return "Jó munkát! :)";
  }
}