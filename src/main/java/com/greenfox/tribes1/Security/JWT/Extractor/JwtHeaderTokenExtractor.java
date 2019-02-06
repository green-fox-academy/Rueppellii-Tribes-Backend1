package com.greenfox.tribes1.Security.JWT.Extractor;

import org.springframework.stereotype.Component;

@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {
  public static String HEADER_PREFIX = "Bearer ";

  @Override
  public String extract(String header) {
    return header.substring(HEADER_PREFIX.length(), header.length());
  }
}
