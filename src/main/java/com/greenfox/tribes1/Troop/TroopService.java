package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Troop.Model.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroopService {
  private TroopRepository troopRepository;

  @Autowired
  public TroopService(TroopRepository troopRepository) {
    this.troopRepository = troopRepository;
  }

  public Troop save(Troop troop) {
    if (troop == null) {
      return null;
    }
    return troopRepository.save(troop);
  }
}
