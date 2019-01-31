package com.greenfox.tribes1.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.tribes1.Security.JWT.Extractor.TokenExtractor;
import com.greenfox.tribes1.Security.JWT.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
  public static final String AUTHENTICATION_URL = "/api/auth/login";
  public static final String REFRESH_TOKEN_URL = "/api/auth/token";
  public static final String API_ROOT_URL = "/api/**";

  private UserDetailsServiceImpl userDetailsService;
  private JwtAuthenticationProvider jwtAuthenticationProvider;
  private TokenExtractor tokenExtractor;
  private AuthenticationManager authenticationManager;
  private ObjectMapper objectMapper;

  public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationProvider jwtAuthenticationProvider, TokenExtractor tokenExtractor, AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
    this.userDetailsService = userDetailsService;
    this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    this.tokenExtractor = tokenExtractor;
    this.authenticationManager = authenticationManager;
    this.objectMapper = objectMapper;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
            .antMatchers( "/**").permitAll()
            .anyRequest().authenticated();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
