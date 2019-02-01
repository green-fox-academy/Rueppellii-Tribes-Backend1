package com.greenfox.tribes1.Security.JWT;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class JwtSettings {

  public static final long ACCESS_TOKEN_LIFETIME = 30 * 60 * 1000L;
  public static final long REFRESH_TOKEN_LIFETIME = 30 * 24 * 60 * 60 * 1000L;

  public static final String TOKEN_ISSUER = "Springles";

  public static final String TOKEN_SIGNING_KEY = System.getenv("TRIBES_TOKEN_SIGNING_KEY");

}
