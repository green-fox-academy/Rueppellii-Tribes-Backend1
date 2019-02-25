package com.greenfox.tribes1.ApplicationUser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplicationUser {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  @JsonIgnore
  private String password;
  private String userEmail;
  
  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "kingdom_id")
  private Kingdom kingdom;

  //@ManyToMany
 // private Set<Role> roles;
  
  public ApplicationUser(String username, String password, String userEmail) {
    this.username = username;
    this.password = password;
    this.userEmail = userEmail;
  }
}
