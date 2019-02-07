package com.greenfox.tribes1;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.ApplicationUser.ApplicationUserRepository;
import com.greenfox.tribes1.ApplicationUser.ApplicationUserService;
import com.greenfox.tribes1.Security.Model.AccessJwtToken;
import com.greenfox.tribes1.Security.Model.JwtTokenFactory;
import com.greenfox.tribes1.Security.Model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestTokenProvider {

  ApplicationUserRepository applicationUserRepository;

  JwtTokenFactory jwtTokenFactory;

  @Autowired
  public TestTokenProvider(ApplicationUserRepository applicationUserRepository, JwtTokenFactory jwtTokenFactory) {
    this.applicationUserRepository = applicationUserRepository;
    this.jwtTokenFactory = jwtTokenFactory;
  }

  private UserContext getUserContext(String username, ApplicationUserService userService) {
    ApplicationUser applicationUser = userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User NOT found"));
    return UserContext.create(applicationUser.getUsername(), Collections.emptyList());
  }

  public String getTestRefreshToken(String username, ApplicationUserService userService, JwtTokenFactory tokenFactory) {
    UserContext userContext = getUserContext(username, userService);
    TokenDTO tokenDTO = (TokenDTO) tokenFactory.createRefreshToken(userContext);
    String token = "Bearer " + tokenDTO.getJwtToken().getToken();
    return token;
  }

  public String getTestExpiredRefreshToken(String username, ApplicationUserService userService, JwtTokenFactory tokenFactory) {
    UserContext userContext = getUserContext(username, userService);
    long tokenLifetimeInMilliseconds = 1L;
    TokenDTO tokenDTO = (TokenDTO) tokenFactory.createTestRefreshToken(userContext, tokenLifetimeInMilliseconds);
    String token = "Bearer " + tokenDTO.getJwtToken().getToken();
    return token;
  }

  public String getTestAccessToken(String username, ApplicationUserService userService, JwtTokenFactory tokenFactory) {
    UserContext userContext = getUserContext(username, userService);
    AccessJwtToken tokenDTO = tokenFactory.createAccessJwtToken(userContext);
    String token = "Bearer " + tokenDTO.getRawToken();
    return token;
  }

  public String createMockToken(String username) {
    UserContext userContext = UserContext.create(username, Collections.emptyList());
    AccessJwtToken token = jwtTokenFactory.createAccessJwtToken(userContext);
    return "Bearer " + token.getToken();
  }
}