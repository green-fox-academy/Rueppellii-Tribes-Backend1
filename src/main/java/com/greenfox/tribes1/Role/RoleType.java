package com.greenfox.tribes1.Role;

public enum RoleType {
  ADMIN, USER, SUPERUSER;

  public String authority(){
  return "ROLE_" + this.name();
  }
}
