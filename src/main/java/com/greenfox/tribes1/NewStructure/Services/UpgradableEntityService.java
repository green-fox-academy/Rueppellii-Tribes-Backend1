package com.greenfox.tribes1.NewStructure.Services;

import com.greenfox.tribes1.TimeService;
import com.greenfox.tribes1.Upgradable;
import com.greenfox.tribes1.kingdomelement.KingdomElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class UpgradableEntityService extends EntityService implements Upgradable<KingdomElement> {

  @Autowired
  public UpgradableEntityService(JpaRepository repository, TimeService timeService) {
    super(repository, timeService);
  }

  @Override
  public void upgrade(KingdomElement kingdomElement) {

  }

  @Override
  protected Exception notFoundException() {
    return null;
  }
}
