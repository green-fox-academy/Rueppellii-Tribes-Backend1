package com.greenfox.tribes1.kingdomelement.Resources;

import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.TimeService;
import com.greenfox.tribes1.kingdomelement.KingdomElementService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ResourceService extends KingdomElementService<Resource> {

  private ResourceRepository resourceRepository;
  private TimeService timeService;
  private Timestamp updatedAt;

  @Autowired
  protected ResourceService(ResourceRepository repository, TimeService timeService) {
    super(repository, timeService);
  }

  @SneakyThrows
  public final void refresh(Resource resource) {
    Long difference = timeService.calculateDifference(updatedAt, new Timestamp(System.currentTimeMillis()));
    if (difference != null && difference > 0) {
      //TODO: Resource and progression model have to implement Updatable
      resource.update(difference);
      resourceRepository.save(resource);
    }
  }

  @Override
  protected Exception notFoundException() {
    return new NotValidResourceException("Invalid resource type");
  }
}