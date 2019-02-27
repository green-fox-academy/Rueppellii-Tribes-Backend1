package com.greenfox.tribes1.NewStructure.Services;

import com.greenfox.tribes1.TimeService;
import com.greenfox.tribes1.kingdomelement.Resources.Resource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Optional;

public class RefreshableEntityService<T> extends EntityService implements Refreshable<T> {

  private T t;

  @Autowired
  public RefreshableEntityService(JpaRepository repository, TimeService timeService) {
    super(repository, timeService);
  }

  @SneakyThrows
  public void refresh(Resource resource) {

  }

}

  @Override
  protected Exception notFoundException() {
    return new Exception();
  }

  @Override
  public void refresh(T t) {
    Long difference = timeService.calculateDifference(resource.getUpdated_at(), new Timestamp(System.currentTimeMillis()));
    if (difference > 0) {
      resource.update(difference);
      save(Optional.of(resource));
    }
  }
