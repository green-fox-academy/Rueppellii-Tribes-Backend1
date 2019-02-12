package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.KingdomElementService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class ResourceService implements KingdomElementService<KingdomResource> {

  private ResourceRepository resourceRepository;
  private Predicate<KingdomResource> isValid = (a) -> (a != null);

  @Autowired
  public ResourceService(ResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }

  @Override
  public void upgrade(KingdomResource kingdomResource) {

  }

  @Override
  @SneakyThrows
  public KingdomResource findById(Long id) {
    return Optional.of(resourceRepository.findById(id)).get().orElseThrow(()
            -> new NotValidResourceException("There is no Building with such Id"));
  }

  @Override
  public KingdomResource save(KingdomResource kingdomResource) throws NotValidResourceException {
    if (isValid.test(kingdomResource)) {
      return resourceRepository.save(kingdomResource);
    }
    throw new NotValidResourceException("Resource validation failed");
  }

  @Override
  public void update(KingdomResource kingdomResource) throws NotValidResourceException {
    kingdomResource.setAmount(kingdomResource.getAmount() + kingdomResource.update());
    save(kingdomResource);
  }
}