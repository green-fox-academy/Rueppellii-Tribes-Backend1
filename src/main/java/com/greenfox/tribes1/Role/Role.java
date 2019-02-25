package com.greenfox.tribes1.Role;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private RoleType roleType;

  @ManyToMany(mappedBy = "roles")
  private List<ApplicationUser> users;

}
