package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.ApplicationUser.ApplicationUser;
import com.greenfox.tribes1.Exception.NotValidResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ResourceService {
  
  private ResourceRepository resourceRepository;
  
  @Autowired
  public ResourceService(ResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }
  
  public KingdomResource saveResource(KingdomResource kingdomResource) throws NotValidResourceException {
    if (validResource(kingdomResource)) {
      return resourceRepository.save(kingdomResource);
    }
    throw new NotValidResourceException("Resource validation failed");
  }
  
  private boolean validResource(KingdomResource resource) {
    return resource != null;
  }
  
  Integer findByResourceType(KingdomResource resource, String type) {
    
    return null;
    
  }
  
  private void updateDatabase(Timestamp currentTime, Timestamp beginTime, ApplicationUser applicationUser) {
    //when an endpoint is called, this method should be called as well
    //applicationUser.getKingdom().getResources().
    applicationUser.getKingdom().getResources().;
    resourceRepository.save(gold);
    resourceRepository.save(food);
  }
}