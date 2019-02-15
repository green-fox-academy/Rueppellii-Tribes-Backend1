package com.greenfox.tribes1.Security;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

public class CustomCorsFilter extends CorsFilter {

   public CustomCorsFilter() {
       super(configurationSource());
   }

  public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
  private static final String AUTHENTICATION_URL = "/login";
  private static final String REGISTRATION_URL = "/register";
  private static final String REFRESH_TOKEN_URL = "/refreshtoken";
  private static final String API_ROOT_URL = "/**";

  List<String> permitAllEndpointList = Arrays.asList(
          AUTHENTICATION_URL,
          REFRESH_TOKEN_URL,
          REGISTRATION_URL);

   private static UrlBasedCorsConfigurationSource configurationSource() {
       CorsConfiguration config = new CorsConfiguration();
       config.setAllowCredentials(true);
       config.addAllowedOrigin("http://localhost:3000");
       config.addAllowedHeader("*");
       config.setMaxAge(36000L);
       config.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"));
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", config);
       return source;
   }
}