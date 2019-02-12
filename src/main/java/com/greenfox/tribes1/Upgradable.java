package com.greenfox.tribes1;

public interface Upgradable<T> {
  public void upgrade(T t) throws Exception;

}
