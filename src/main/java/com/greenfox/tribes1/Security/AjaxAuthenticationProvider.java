package com.greenfox.tribes1.Security;

import com.greenfox.tribes1.Security.Model.UserContext;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static java.util.Collections.emptyList;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

  private final BCryptPasswordEncoder encoder;
  @Autowired
  private final UserDetailsServiceImpl userDetailsService;

  public AjaxAuthenticationProvider(BCryptPasswordEncoder encoder, UserDetailsServiceImpl userDetailsService) {
    this.encoder = encoder;
    this.userDetailsService = userDetailsService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Assert.notNull(authentication, "No authentication data provided");
    String username = (String) authentication.getPrincipal();
    String password = (String) authentication.getCredentials();

    User user = (User) userDetailsService.loadUserByUsername(username);

    UserContext userContext = UserContext.create(user.getUsername(), emptyList());
    return new UsernamePasswordAuthenticationToken(userContext, null, emptyList());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }
}
