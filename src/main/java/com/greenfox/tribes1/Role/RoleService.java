package com.greenfox.tribes1.Role;

import com.greenfox.tribes1.Exception.RoleNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
public class RoleService {

  private RoleRepository roleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public Role findByRoleType(RoleType roleType) throws RoleNotExistException {
    return roleRepository.findByRoleType(roleType).orElseThrow(()-> new RoleNotExistException("Role does not exist"));
  }
}
