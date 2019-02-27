package com.greenfox.tribes1.kingdomelement.Resources;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceFactory {

  public static Resource createResource(ResourceType resourceType) {
    return resourceType.generateResource();
  }
}