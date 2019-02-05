package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Exception.NotValidKingdomNameException;
import com.greenfox.tribes1.Kingdom.DTO.KingdomDTO;
import com.greenfox.tribes1.Kingdom.DTO.KingdomResourceDTO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class KingdomService {
  
  private KingdomRepository kingdomRepository;
  
  @Autowired
  public KingdomService(KingdomRepository kingdomRepository) {
    this.kingdomRepository = kingdomRepository;
  }
  
  public Kingdom saveKingdom(Kingdom kingdom) throws NotValidKingdomNameException {
    if (kingdom.getName() == null || !validKingdomName(kingdom.getName())) {
      throw new NotValidKingdomNameException("The given name wasn't correct, or the field is empty!");
    }
    return kingdomRepository.save(kingdom);
  }
  
  public boolean validKingdomName(String field) {
    return field.equals("Narnia") || field.equals("Rueppellii");
  }
  
  public Kingdom findKingdomByApplicationUser(ApplicationUser applicationUser) {
    return kingdomRepository.findKingdomByApplicationUser(applicationUser);
  }
  
  public Kingdom findKingdomByApplicationUserName(String applicationUserName) {
    return kingdomRepository.findKingdomByApplicationUser_Username(applicationUserName);
  }
  
  public KingdomDTO createKingdomDTOFromKingdom(Kingdom kingdom) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(kingdom, KingdomDTO.class);
  }
  
  public KingdomResourceDTO createKingdomResourceDTOFromKingdom(Kingdom kingdom) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(kingdom, KingdomResourceDTO.class);
  }
  
  public List<Kingdom> findAll() {
    return kingdomRepository.findAll();
  }
  
  public Kingdom renameKingdom(Kingdom kingdom, String newKingdomName) {
    if (validKingdomName(newKingdomName)) {
      kingdom.setName(newKingdomName);
      kingdomRepository.save(kingdom);
    }
    return kingdom;
  }
}