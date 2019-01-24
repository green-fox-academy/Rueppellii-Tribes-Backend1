package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.Exception.NotValidKingdomNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomService {

  private KingdomRepository kingdomRepository;

  @Autowired
  public KingdomService(KingdomRepository kingdomRepository) {
    this.kingdomRepository = kingdomRepository;
  }
  
  public Kingdom saveKingdom(Kingdom kingdom) throws NotValidKingdomNameException {
    if (!validKingdomName(kingdom.getName())) {
      throw new NotValidKingdomNameException("The given name wasn't correct, or the field is empty!");
    }
    return kingdomRepository.save(kingdom);
  }

  public boolean validKingdomName(String field) {
    return field == "Narnia" || field == "Rueppellii";
  }
}
