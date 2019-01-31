package com.greenfox.tribes1.Security.JWT;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
//@ConfigurationProperties(prefix = "tribes1.security.jwt")
public class JwtSettings {

  private Integer tokenExpirationTime;

  private String tokenIssuer;

  private String tokenSigningKey;

  private Integer refreshTokenExpTime;
}
