package com.greenfox.tribes1;

import java.util.function.Predicate;

public interface KingdomElementService<T> {

  public T upgrade();

  Predicate isValid = t -> t != null;

  public T findById();

  public T save();
}
