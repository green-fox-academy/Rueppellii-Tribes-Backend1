package com.greenfox.tribes1.Security.Model;

import com.greenfox.tribes1.ApplicationUser.ApplicationUserRepository;
import com.greenfox.tribes1.ApplicationUser.ApplicationUserService;
import com.greenfox.tribes1.Security.Config.JwtSettings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtTokenFactory {

  private final JwtSettings jwtsettings;
  private ApplicationUserService applicationUserService;

  @Autowired
  public JwtTokenFactory(JwtSettings settings, ApplicationUserService applicationUserService) {
    this.jwtsettings = settings;
    this.applicationUserService = applicationUserService;
  }

  public AccessJwtToken createAccessJwtToken(UserContext userContext) {
    Claims claims = Jwts.claims().setSubject(userContext.getUsername());
    claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

    LocalDateTime currentTime = LocalDateTime.now();

    String token = Jwts.builder()
            .setClaims(claims)
            .setIssuer(jwtsettings.TOKEN_ISSUER)
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(Date.from(currentTime
                    .plusMinutes(jwtsettings.ACCESS_TOKEN_LIFETIME)
                    .atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(SignatureAlgorithm.HS512, jwtsettings.TOKEN_SIGNING_KEY)
   // jwtsettings.TOKEN_SIGNING_KEY

            .compact();
    return new AccessJwtToken(token, claims);
  }

  public JwtToken createRefreshToken(UserContext userContext) {
    LocalDateTime currentTime = LocalDateTime.now();

    Claims claims = Jwts.claims().setSubject(userContext.getUsername());
    claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));

    String token = Jwts.builder()
            .setClaims(claims)
            .setIssuer(jwtsettings.TOKEN_ISSUER)
            .setId(UUID.randomUUID().toString())
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(Date.from(currentTime
                    .plusMinutes(jwtsettings.REFRESH_TOKEN_LIFETIME)
                    .atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(SignatureAlgorithm.HS512, jwtsettings.TOKEN_SIGNING_KEY)
            .compact();

    return new AccessJwtToken(token, claims);
  }

  public JwtToken createTestRefreshToken(UserContext userContext, Long tokenLifetimeInMilliseconds) {
    LocalDateTime currentTime = LocalDateTime.now();
    Date refreshTokenExpirationTime = Date.from(currentTime.atZone(ZoneId.systemDefault())
            .plus(tokenLifetimeInMilliseconds, ChronoUnit.MILLIS).toInstant());
    Claims claims = Jwts.claims().setSubject(userContext.getUsername());
    claims.put("scopes", Arrays.asList(Scopes.REFRESH_TOKEN.authority()));
    Long userId = applicationUserService.getIdFromDB(userContext.getUsername());
/*    StringBuilder userIdForToken = new StringBuilder();
    userIdForToken.append(userId);*/

    String token = Jwts.builder()
            .setClaims(claims)
            .setIssuer(jwtsettings.TOKEN_ISSUER)
            .setId(userId.toString())
            .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
            .setExpiration(refreshTokenExpirationTime)
            .signWith(SignatureAlgorithm.HS512, jwtsettings.TOKEN_SIGNING_KEY)
            .compact();

    return new AccessJwtToken(token, claims);
  }
}
