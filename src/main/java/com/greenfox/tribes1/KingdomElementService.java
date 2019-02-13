package com.greenfox.tribes1;

import java.util.Optional;

public interface KingdomElementService<T> {

  public T findById(Long id);

  public T save(Optional<T> object);

  public void update(T t);
}