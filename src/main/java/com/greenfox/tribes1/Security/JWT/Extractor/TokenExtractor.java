package com.greenfox.tribes1.Security.JWT.Extractor;

public interface TokenExtractor {
  public String extract(String payload);
}
