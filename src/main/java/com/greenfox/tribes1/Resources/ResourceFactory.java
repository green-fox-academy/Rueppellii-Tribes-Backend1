package com.greenfox.tribes1.Resources;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceFactory {
  
public KingdomResource getResource(ResourceType resourceType) {
    return resourceType.generateResource();
  }
}