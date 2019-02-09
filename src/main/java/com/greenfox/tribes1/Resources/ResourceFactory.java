package com.greenfox.tribes1.Resources;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceFactory {

  public static KingdomResource createResource(ResourceType resourceType) {
    return resourceType.generateResource();
  }
}