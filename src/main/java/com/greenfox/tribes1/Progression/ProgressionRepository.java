package com.greenfox.tribes1.Progression;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ProgressionRepository extends JpaRepository<Progression, Long> {
//  Progression findById
  List<Progression> findByTypeAndFinished_atIsLessThanAnAndLevelEquals(String type, Timestamp now, Long level);
}
