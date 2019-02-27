package com.greenfox.tribes1.Progression;

import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.kingdomelement.Resources.Updatable;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Progression implements Updatable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long model_id;
  private Long level;
  private String type;
  private Timestamp finished;

  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinColumn(name = "kingdom_id", referencedColumnName = "id")
  private Kingdom kingdom;

  @Override
  public void update(Long difference) {

  }
}
