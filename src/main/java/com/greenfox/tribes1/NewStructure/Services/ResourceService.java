package com.greenfox.tribes1.NewStructure.Services;

import com.greenfox.tribes1.TimeService;
import org.springframework.data.jpa.repository.JpaRepository;

public class ResourceService extends RefreshableEntityService {

  public ResourceService(JpaRepository repository, TimeService timeService) {
    super(repository, timeService);
  }

}
