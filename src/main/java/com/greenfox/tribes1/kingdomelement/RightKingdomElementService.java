package com.greenfox.tribes1.kingdomelement;

import com.greenfox.tribes1.TimeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class RightKingdomElementService<T extends KingdomElement> {

  private JpaRepository<T, Long> repository;
  private TimeService timeService;

  protected abstract Exception notFoundException();

  @SneakyThrows
  public final T save(T entity) {
    T entityToSave = Optional.ofNullable(entity)
            .orElseThrow(this::notFoundException);

    return repository.save(entityToSave);
  }

  @SneakyThrows
  public final void refresh(T entity) {
    Long difference = timeService.calculateDifference(entity.getUpdatedAt(), new Timestamp(System.currentTimeMillis()));
    if (difference != null && difference > 0) {
      // refreshInner(entity);
      entity.update(difference);
      save(entity);
    }
  }

  protected void refreshInner(T entity) {
  }
}