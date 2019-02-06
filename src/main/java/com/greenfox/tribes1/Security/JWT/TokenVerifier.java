package com.greenfox.tribes1.Security.JWT;

public interface TokenVerifier {
  public boolean verify(String jti);
}
