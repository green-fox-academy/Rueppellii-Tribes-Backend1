package com.greenfox.tribes1.Security.JWT.Extractor;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {
  public static String HEADER_PREFIX = "Bearer ";

  @Override
  public String extract(String header) {
    if (header.isEmpty() || header == null) {
      throw new AuthenticationServiceException("Authorization header cannot be blank!");
    }
    return header.substring(HEADER_PREFIX.length(), header.length());
  }
}
