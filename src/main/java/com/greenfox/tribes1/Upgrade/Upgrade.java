package com.greenfox.tribes1.Upgrade;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

//@Entity
@Getter
@Setter
public class Upgrade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long kingdomId;
  private Long troopId;
  private Long buildingId;
  private Boolean isCreate;
  private Timestamp timestamp;
}
