package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Exception.NotValidKingdomNameException;
import com.greenfox.tribes1.Kingdom.DTO.KingdomDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KingdomService {
  
  private KingdomRepository kingdomRepository;
  
  @Autowired
  public KingdomService(KingdomRepository kingdomRepository) {
    this.kingdomRepository = kingdomRepository;
  }
  
  Kingdom saveKingdom(Kingdom kingdom) throws NotValidKingdomNameException {
    if (kingdom.getName() == null || !validKingdomName(kingdom.getName())) {
      throw new NotValidKingdomNameException("The given name wasn't correct, or the field is empty!");
    }
    return kingdomRepository.save(kingdom);
  }
  
  private boolean validKingdomName(String field) {
    return field.equals("Narnia") || field.equals("Rueppellii");
  }
  
  Kingdom findKingdomByApplicationUser(ApplicationUser applicationUser) {
    return kingdomRepository.findKingdomByApplicationUser(applicationUser);
  }
  
  Kingdom findKingdomByApplicationUserName(String applicationUserName) {
    return kingdomRepository.findKingdomByApplicationUser_Username(applicationUserName);
  }
  
  KingdomDTO createKingdomDTOFromKingdom(Kingdom kingdom) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(kingdom, KingdomDTO.class);
  }
  
  List<Kingdom> findAll() {
    return kingdomRepository.findAll();
  }
}