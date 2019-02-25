package com.greenfox.tribes1.HttpRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"all"
})
@Getter
@Setter
public class Clouds {

@JsonProperty("all")
private Integer all;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}