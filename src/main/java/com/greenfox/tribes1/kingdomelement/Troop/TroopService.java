package com.greenfox.tribes1.kingdomelement.Troop;

import com.greenfox.tribes1.Exception.TroopNotValidException;
import com.greenfox.tribes1.TimeService;
import com.greenfox.tribes1.Upgradable;
import com.greenfox.tribes1.kingdomelement.KingdomElementService;
import com.greenfox.tribes1.kingdomelement.Troop.Model.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TroopService extends KingdomElementService<Troop> implements Upgradable<Troop> {

  @Autowired
  protected TroopService(JpaRepository<Troop, Long> repository, TimeService timeService) {
    super(repository, timeService);
  }

  @Override
  protected Exception notFoundException() {
    return new TroopNotValidException("Invalid resource type");
  }

  @Override
  public void upgrade(Troop troop) {
    troop.levelUp();
  }

}