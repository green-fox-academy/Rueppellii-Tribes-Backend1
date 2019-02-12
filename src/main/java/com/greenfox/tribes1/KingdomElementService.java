package com.greenfox.tribes1;

public interface KingdomElementService<T> {

  //TODO: throws Expression is not OK here

  public void upgrade(T t) throws Exception;

  public T findById(Long id) throws Exception;

  public T save(T t) throws Exception;

  public void update(T t) throws Exception;
}
