package com.greenfox.tribes1.Troop;

import com.greenfox.tribes1.Exception.TroopIdNotFoundException;
import com.greenfox.tribes1.Exception.TroopNotValidException;
import com.greenfox.tribes1.KingdomElementService;
import com.greenfox.tribes1.Resources.KingdomResource;
import com.greenfox.tribes1.Troop.Model.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

@Service
public class TroopService implements KingdomElementService<Troop> {

  private Predicate<Troop> isValid = (a) -> (a != null);
  private TroopRepository troopRepository;

  @Autowired
  public TroopService(TroopRepository troopRepository) {
    this.troopRepository = troopRepository;
  }

  @Override
  public void update(Troop troop) throws Exception {
  //TODO
  }

  @Override
  public void upgrade(Troop troop) throws TroopNotValidException {
    troop.setLevel(troop.getLevel() + 1L);
    troop.setHP(troop.getHP() * 1.1F);
    save(troop);
  }

  @Override
  public Troop findById(Long id) throws TroopIdNotFoundException {
    return Optional.of(troopRepository.findById(id)).get().orElseThrow(()
            -> new TroopIdNotFoundException(("There is no Troop with such Id")));
  }

  @Override
  public Troop save(Troop troop) throws TroopNotValidException {
    //TODO: refactor
    if (isValid.test(troop)) {
      return troopRepository.save(troop);
    }
    throw new TroopNotValidException("Troop is not valid");
  }
}
