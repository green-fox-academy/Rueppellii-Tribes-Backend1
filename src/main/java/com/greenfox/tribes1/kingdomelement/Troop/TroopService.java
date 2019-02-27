package com.greenfox.tribes1.kingdomelement.Troop;

import com.greenfox.tribes1.Exception.TroopIdNotFoundException;
import com.greenfox.tribes1.Exception.TroopNotValidException;
import com.greenfox.tribes1.kingdomelement.RightKingdomElementService;
import com.greenfox.tribes1.kingdomelement.Troop.Model.Troop;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class TroopService extends RightKingdomElementService<Troop> {

  private TroopRepository troopRepository;

  @Autowired
  TroopService(TroopRepository troopRepository) {
    this.troopRepository = troopRepository;
  }

  @Override
  public void refresh(Troop troop) throws Exception {
    //TODO: IF any progression is over, create new Building();
  }

  @Override
  @SneakyThrows
  public void upgrade(Troop troop) {
    troop.levelUp();
    save(Optional.of(troop));
  }

  @Override
  public Troop findById(Long id) throws TroopIdNotFoundException {
    return troopRepository.findById(id)
            .orElseThrow(() -> new TroopIdNotFoundException(("There is no Troop with such Id")));
  }

  @Override
  protected Exception notFoundException() {
    return null;
  }

  @Override
  public Troop save(Optional<Troop> troop) throws TroopNotValidException {
    return (troopRepository.save(troop
            .orElseThrow(() -> new TroopNotValidException("Troop is not valid!"))));
  }

}