package com.greenfox.tribes1.ApplicationUser;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class ApplicationUser {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  private String userEmail;
  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  private Kingdom kingdom;
  
  public ApplicationUser(String username, String password, String userEmail) {
    this.username = username;
    this.password = password;
    this.userEmail = userEmail;
  }
}
