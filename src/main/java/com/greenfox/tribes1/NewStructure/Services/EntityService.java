package com.greenfox.tribes1.NewStructure.Services;

import com.greenfox.tribes1.TimeService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class EntityService<T> {

  protected JpaRepository<T, Long> repository;
  protected TimeService timeService;

  @Autowired
  public EntityService(JpaRepository<T, Long> repository, TimeService timeService) {
    this.repository = repository;
    this.timeService = timeService;
  }

  @SneakyThrows
  public final T save(T entity) {
    T entityToSave = Optional.ofNullable(entity)
            .orElseThrow(this::notFoundException);

    return repository.save(entityToSave);
  }

  protected abstract Exception notFoundException();

  public final Optional<T> findById(Long id) {
    return repository.findById(id);
  }
}
