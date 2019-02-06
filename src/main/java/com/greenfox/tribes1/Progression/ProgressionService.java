package com.greenfox.tribes1.Progression;

import com.greenfox.tribes1.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgressionService {
  private ProgressionRepository progressionRepository;
  private TimeService timeService;

  @Autowired
  public ProgressionService(ProgressionRepository progressionRepository, TimeService timeService) {
    this.progressionRepository = progressionRepository;
    this.timeService = timeService;
  }

  public Progression findById (Long id) {
    return progressionRepository.findById(id).orElse(null);
  }

  public void saveProgression (Progression progression) {
    progression.setFinished_at(timeService.calculateBuildingTimeForNewBuildingOrTroop(progression));
    progressionRepository.save(progression);
  }

  public List<Progression> findAllProgressionModel() {
    return progressionRepository.findAll();
  }

  public List<Progression> findProgressionsWithExpiredTimestamp () {
    List<Progression> progressionsWithExpiredTimestamp = new ArrayList<>();
    List<Progression> allProgression = progressionRepository.findAll();
    for (int i = 0; i < allProgression.size(); i++) {
      if (timeService.isTimestampExpired(allProgression.get(i).getFinished_at())) {
//        --> progression.isCreate true & type==building --> kingdomservice.addNewBuilding
//        --> progression.isCreate true & type==troop --> kingdomservice.addNewTroop
//        --> progression.isCreate false & type==building --> kingdomservice.upgradeBuilding
//        --> progression.isCreate false & type==troop --> kingdomservice.upgradeTroop
      }

    }
    return null;
  }

  public void createOrUpgradeModel_FromProgressionWithExpiredTimestamp_AndDeleteProgressionFromDatabase() {

  }
}
