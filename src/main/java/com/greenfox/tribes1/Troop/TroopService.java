package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Exception.TroopIdNotFoundException;
import com.greenfox.tribes1.Exception.TroopNotValidException;
import com.greenfox.tribes1.KingdomElementService;
import com.greenfox.tribes1.Troop.Model.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TroopService implements KingdomElementService<Troop> {

  private TroopRepository troopRepository;

  @Autowired
  public TroopService(TroopRepository troopRepository) {
    this.troopRepository = troopRepository;
  }

  public Troop save(Troop troop) throws TroopNotValidException {
    if (isValidTroop(troop)) {
      return troopRepository.save(troop);
    }
    throw new TroopNotValidException("Troop is not valid");
  }

  public boolean isValidTroop(Troop troop) {
    return troop != null;
  }

  public Troop findById(Long id) throws TroopIdNotFoundException {
    return Optional.of(troopRepository.findById(id)).get().orElseThrow(()
            -> new TroopIdNotFoundException(("There is no Troop with such Id")));
  }

  public void upgradeTroop(Troop troopToUpgrade) throws TroopNotValidException {
    troopToUpgrade.setLevel(troopToUpgrade.getLevel() + 1L);
    troopToUpgrade.setHP(troopToUpgrade.getHP() * 1.1F);
    save(troopToUpgrade);
  }

  @Override
  public Troop upgrade() {
    return null;
  }
  
  @Override
  public Troop findById() {
    return null;
  }

  @Override
  public Troop save() {
    return null;
  }
}
