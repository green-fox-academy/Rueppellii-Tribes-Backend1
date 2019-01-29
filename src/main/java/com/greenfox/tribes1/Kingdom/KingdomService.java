package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.ApplicationUser.DTO.ApplicationUserWithKingdomDTO;
import com.greenfox.tribes1.Exception.NotValidKingdomNameException;
import com.greenfox.tribes1.Kingdom.DTO.KingdomDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public Kingdom findByApplicationUser(ApplicationUser applicationUser) {
    return kingdomRepository.findByApplicationUser(applicationUser);
  }

  public KingdomDTO createKingdomDTOFromKingdom(Kingdom kingdom) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(kingdom, KingdomDTO.class);
  }

  public List<Kingdom> findAll() {
    return kingdomRepository.findAll();
  }
}
