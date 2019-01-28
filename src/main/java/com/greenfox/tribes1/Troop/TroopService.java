package com.greenfox.tribes1.Troop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroopService {
  private TroopRepository troopRepository;

  @Autowired
  public TroopService(TroopRepository troopRepository) {
    this.troopRepository = troopRepository;
  }

  public void save(Troop troop) {
    if (!troop.equals(null)) {
      troopRepository.save(troop);
    }
  }
}
