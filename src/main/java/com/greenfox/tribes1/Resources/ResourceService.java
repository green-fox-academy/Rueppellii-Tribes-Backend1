package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.Kingdom.KingdomRepository;
import com.greenfox.tribes1.KingdomElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService implements KingdomElementService<KingdomResource> {

  private ResourceRepository resourceRepository;
  @Autowired
  private KingdomRepository kingdomRepository;

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

  public void updateResource(KingdomResource kingdomResource) throws NotValidResourceException {
    kingdomResource.setAmount(kingdomResource.getAmount() + kingdomResource.update());
    saveResource(kingdomResource);
  }

  @Override
  public KingdomResource upgrade() {
    return null;
  }

  @Override
  public KingdomResource isValid() {
    return null;
  }

  @Override
  public KingdomResource findById() {
    return null;
  }

  @Override
  public KingdomResource save() {
    return null;
  }
}