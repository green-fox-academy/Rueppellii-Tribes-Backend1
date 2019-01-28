package com.greenfox.tribes1.ApplicationUser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
}
