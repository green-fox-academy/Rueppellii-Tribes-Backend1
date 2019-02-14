package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.KingdomElementService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ResourceService implements KingdomElementService<Resource> {

  private ResourceRepository resourceRepository;
  private Predicate<Resource> isValid = (a) -> (a != null);

  @Autowired
  public ResourceService(ResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }

  @Override
  @SneakyThrows
  public Resource findById(Long id) {

    return resourceRepository.findById(id).orElseThrow(()
            -> new NotValidResourceException("There is no Building with such Id"));
  }

  @Override
  public Resource save(Optional<Resource> resource) throws NotValidResourceException {
    return resourceRepository.save(resource
            .orElseThrow(() -> new NotValidResourceException("Resource validation failed")));
  }

  @Override
  public void refresh(Resource kingdomResource) throws NotValidResourceException {
    kingdomResource.update();
    save(Optional.of(kingdomResource));
  }

  public void lvlUp(Resource resource) {
    resource.setAmountPerMinute(resource.getAmountPerMinute() + 5L);
  }
}