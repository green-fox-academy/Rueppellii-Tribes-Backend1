package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.DateNotGivenException;
import com.greenfox.tribes1.Exception.NotValidResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  
  public void updateResource(KingdomResource kingdomResource) throws DateNotGivenException, NotValidResourceException {
    kingdomResource.setAmount(kingdomResource.getAmount() + kingdomResource.update());
    saveResource(kingdomResource);
  }
  
}