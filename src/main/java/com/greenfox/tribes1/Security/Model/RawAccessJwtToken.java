package com.greenfox.tribes1.Security.Model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class RawAccessJwtToken implements JwtToken {
  private String token;

  public RawAccessJwtToken(String token) {
    this.token = token;
  }

  public Jws<Claims> parseClaims(String signingKey) {
    return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
  }

  @Override
  public String getToken() {
    return token;
  }
}
