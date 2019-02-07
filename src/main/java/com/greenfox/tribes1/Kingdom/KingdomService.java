package com.greenfox.tribes1.Kingdom;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Building.Building;
import com.greenfox.tribes1.Building.BuildingFactory;
import com.greenfox.tribes1.Building.BuildingType;
import com.greenfox.tribes1.Exception.NotValidKingdomNameException;
import com.greenfox.tribes1.Kingdom.DTO.KingdomDTO;
import com.greenfox.tribes1.Resources.KingdomResource;
import com.greenfox.tribes1.Resources.ResourceFactory;
import com.greenfox.tribes1.Resources.ResourceType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KingdomService {
  
  private KingdomRepository kingdomRepository;
  
  @Autowired
  public KingdomService(KingdomRepository kingdomRepository) {
    this.kingdomRepository = kingdomRepository;
  }
  
  public Kingdom saveKingdom(Kingdom kingdom) throws NotValidKingdomNameException {
    if (kingdom.getName() == null ) {
      throw new NotValidKingdomNameException("The given name wasn't correct, or the field is empty!");
    }
    return kingdomRepository.save(kingdom);
  }
  
  public Kingdom findKingdomByApplicationUserName(String applicationUserName) {
    return kingdomRepository.findKingdomByApplicationUser_Username(applicationUserName);
  }
  
  public KingdomDTO createKingdomDTOFromKingdom(Kingdom kingdom) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(kingdom, KingdomDTO.class);
  }
  
  public List<Kingdom> findAll() {
    return kingdomRepository.findAll();
  }

  public void setStarterBuildings(Kingdom kingdom) {
    List<Building> buildings = new ArrayList<>();

    Building mine = BuildingFactory.createBuilding(BuildingType.mine);
    Building farm = BuildingFactory.createBuilding(BuildingType.farm);
    Building townHall = BuildingFactory.createBuilding(BuildingType.townHall);
    Building barracks = BuildingFactory.createBuilding(BuildingType.barracks);

    mine.setKingdom(kingdom);
    farm.setKingdom(kingdom);
    townHall.setKingdom(kingdom);
    barracks.setKingdom(kingdom);

    buildings.add(mine);
    buildings.add(farm);
    buildings.add(townHall);
    buildings.add(barracks);

    kingdom.setBuildings(buildings);
  }

  public void setStarterResource(Kingdom kingdom) {
    List<KingdomResource> resources = new ArrayList<>();

    KingdomResource gold = ResourceFactory.createResource(ResourceType.gold);
    KingdomResource food = ResourceFactory.createResource(ResourceType.food);

    gold.setKingdom(kingdom);
    food.setKingdom(kingdom);

    resources.add(gold);
    resources.add(food);

    kingdom.setResources(resources);
  }

}
