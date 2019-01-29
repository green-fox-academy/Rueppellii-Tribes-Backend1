package com.greenfox.tribes1.ApplicationUser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ApplicationUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  @JsonIgnore
  private String password;
  private String userEmail;
  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name="application_user_kingdom",
          joinColumns = @JoinColumn(name ="application_user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "kingdom_id", referencedColumnName = "id"))

  private Kingdom kingdom;

  public ApplicationUser(String username, String password, String userEmail) {
    this.username = username;
    this.password = password;
    this.userEmail = userEmail;
  }


}
