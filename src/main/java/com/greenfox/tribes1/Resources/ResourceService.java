package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.Kingdom.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

  private ResourceRepository resourceRepository;
  @Autowired
  private KingdomRepository kingdomRepository;

  @Autowired
  public ResourceService(ResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }

  public Resource saveResource(Resource kingdomResource) throws NotValidResourceException {
    if (validResource(kingdomResource)) {
      return resourceRepository.save(kingdomResource);
    }
    throw new NotValidResourceException("Resource validation failed");
  }

  private boolean validResource(Resource resource) {
    return resource != null;
  }

  public void updateResource(Resource kingdomResource) throws NotValidResourceException {
    kingdomResource.setAmount(kingdomResource.getAmount() + kingdomResource.update());
    saveResource(kingdomResource);
  }
}