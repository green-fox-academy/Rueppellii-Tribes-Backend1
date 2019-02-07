package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.Kingdom.Kingdom;
import com.greenfox.tribes1.Kingdom.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService {

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

  public boolean validResource(KingdomResource resource) {
    return resource != null;
  }
}