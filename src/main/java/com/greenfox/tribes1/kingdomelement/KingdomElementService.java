package com.greenfox.tribes1.kingdomelement;

import com.greenfox.tribes1.TimeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class KingdomElementService<T extends KingdomElement> {

  private JpaRepository<T, Long> repository;
  private TimeService timeService;

  protected abstract Exception notFoundException();

  @SneakyThrows
  public final T save(T entity) {
    T entityToSave = Optional.ofNullable(entity)
            .orElseThrow(this::notFoundException);

    return repository.save(entityToSave);
  }

  public Optional<T> findById(Long id) {
    return repository.findById(id);
  }

  protected void refreshInner(T entity) {
  }
}