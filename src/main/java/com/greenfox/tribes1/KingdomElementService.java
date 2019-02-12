package com.greenfox.tribes1;

import java.util.Optional;

public interface KingdomElementService<T> {

  //TODO: throws Expression is not OK here

  public T findById(Long id) throws Exception;

  public T save(Optional<T> object) throws Exception;

  public void update(T t) throws Exception;
}
