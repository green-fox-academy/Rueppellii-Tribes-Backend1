package com.greenfox.tribes1.Security;

import com.greenfox.tribes1.Security.Config.JwtSettings;
import com.greenfox.tribes1.Security.Config.WebSecurityConfig;
import com.greenfox.tribes1.Security.JWT.Extractor.TokenExtractor;
import com.greenfox.tribes1.Security.JWT.TokenVerifier;
import com.greenfox.tribes1.Security.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@RestController
public class RefreshTokenEndpoint {
  @Autowired
  private JwtTokenFactory tokenFactory;
  @Autowired
  private JwtSettings jwtSettings;
  @Autowired
  private UserDetailsServiceImpl userService;
  @Autowired
  private TokenVerifier tokenVerifier;
  @Autowired
  @Qualifier("jwtHeaderTokenExtractor")
  private TokenExtractor tokenExtractor;

  @RequestMapping(value = "/refreshtoken", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
  public @ResponseBody
  JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME));

    RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
    RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.TOKEN_SIGNING_KEY).orElseThrow(() -> new UnsupportedOperationException());

    String jti = refreshToken.getJti();
    if (!tokenVerifier.verify(jti)) {
      throw new UnsupportedOperationException();
    }

    String subject = refreshToken.getSubject();
    User user = (User) userService.loadUserByUsername(subject);


    UserContext userContext = UserContext.create(user.getUsername(), Collections.emptyList());

    return tokenFactory.createAccessJwtToken(userContext);
  }
}