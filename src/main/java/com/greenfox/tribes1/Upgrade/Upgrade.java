package com.greenfox.tribes1.Upgrade;

import com.greenfox.tribes1.Kingdom.Kingdom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Upgrade {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Boolean is_created;
  private Timestamp timestamp;
  @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(name = "upgrade_kingdom",
          joinColumns = @JoinColumn(name = "upgrade_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "kingdom_id", referencedColumnName = "id"))
  private Kingdom kingdom;
}
