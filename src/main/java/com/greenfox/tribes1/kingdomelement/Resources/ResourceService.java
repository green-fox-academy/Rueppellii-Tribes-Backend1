package com.greenfox.tribes1.kingdomelement.Resources;

import com.greenfox.tribes1.Exception.NotValidResourceException;
import com.greenfox.tribes1.TimeService;
import com.greenfox.tribes1.kingdomelement.RightKingdomElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ResourceService extends RightKingdomElementService<Resource> {

  private ResourceRepository resourceRepository;
  private TimeService timeService;

  @Autowired
  protected ResourceService(JpaRepository<Resource, Long> repository, TimeService timeService) {
    super(repository, timeService);
  }

  @Override
  protected Exception notFoundException() {
    return new NotValidResourceException("Invalid resource type");
  }
}