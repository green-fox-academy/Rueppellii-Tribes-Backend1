package com.greenfox.tribes1.Progression;

import com.greenfox.tribes1.TimeService;
import com.greenfox.tribes1.kingdomelement.KingdomElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressionService {

  private TimeService timeService;
  private ProgressionRepository progressionRepository;

  @Autowired
  public ProgressionService(TimeService timeService, ProgressionRepository progressionRepository) {
    this.timeService = timeService;
    this.progressionRepository = progressionRepository;
  }

  public Progression createProgression(String type) {
    return null;
  }

  public Progression deleteProgression(List<Progression> progressions) {

  }

  public void finishUpgrade(KingdomElement kingdomElement) {

  }

  public void finishConstruction(KingdomElement kingdomElement) {

  }

  public List<Progression> getItemsToCreate(KingdomElement kingdomElement) {

  }

  public Progression save(Progression progression) {
    progressionRepository.save(progression);
  }

}