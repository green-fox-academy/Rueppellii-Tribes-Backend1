package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ResourceService {
  
  private ResourceRepository resourceRepository;
  private TimeService timeService;
  
  @Autowired
  public ResourceService(ResourceRepository resourceRepository, TimeService timeService) {
    this.resourceRepository = resourceRepository;
    this.timeService = timeService;
  }
  
  public KingdomResource saveResource(KingdomResource kingdomResource) throws NotValidResourceException {
    if (validResource(kingdomResource)) {
      return resourceRepository.save(kingdomResource);
    }
    throw new NotValidResourceException("Resource validation failed");
  }
  
  public boolean validResource(KingdomResource resource) {
    return resource != null;
  }
  
  public void updateResource(KingdomResource kingdomResource) throws DateNotGivenException {
    kingdomResource.setAmount(kingdomResource.getAmount() + kingdomResource.update());
  }
  
}