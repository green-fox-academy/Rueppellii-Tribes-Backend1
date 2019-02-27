package com.greenfox.tribes1;

public interface UpgradableKingdomElementService<T extends Upgradable> extends KingdomElementService<T> {

  void upgrade(T t) throws Exception;
}