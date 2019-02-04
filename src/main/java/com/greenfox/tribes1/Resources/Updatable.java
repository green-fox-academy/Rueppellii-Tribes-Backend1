package com.greenfox.tribes1.Resources;

import com.greenfox.tribes1.Exception.DateNotGivenException;

public interface Updatable {
  
  Long update() throws DateNotGivenException;
}
