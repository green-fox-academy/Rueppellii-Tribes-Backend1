package com.greenfox.tribes1.Resources;

public class ResourceFactory {
  
public static KingdomResource createResource(ResourceType resourceType) {
    return resourceType.generateResource();
  }
}