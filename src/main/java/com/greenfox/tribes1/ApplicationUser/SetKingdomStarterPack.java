//package com.greenfox.tribes1.ApplicationUser;
//
//import com.greenfox.tribes1.Building.Building;
//import com.greenfox.tribes1.Building.BuildingFactory;
//import com.greenfox.tribes1.Building.BuildingService;
//import com.greenfox.tribes1.Building.BuildingType;
//import com.greenfox.tribes1.Exception.BuildingNotValidException;
//import com.greenfox.tribes1.Kingdom.Kingdom;
//import com.greenfox.tribes1.Resources.KingdomResource;
//import com.greenfox.tribes1.Resources.ResourceFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class SetKingdomStarterPack {
//  private BuildingService buildingService;
//
//  @Autowired
//  public SetKingdomStarterPack(BuildingService buildingService) {
//    this.buildingService = buildingService;
//  }
//
//  public void setStarterPack(Kingdom myKingdom) throws BuildingNotValidException {
////    myKingdom.setResources(setStarterGold());
//    myKingdom.setBuildings(setStarterBuildings());
//  }
//
//  private List<Building> setStarterBuildings() throws BuildingNotValidException {
//    Building mine = BuildingFactory.createBuilding(BuildingType.mine);
//    buildingService.saveBuilding(mine);
//
//  }
//
////  private static List<KingdomResource> setStarterGold() {
////    return Arrays.asList(
////            ResourceFactory.getResource("gold"),
////            ResourceFactory.getResource("food"));
////  }
//}
