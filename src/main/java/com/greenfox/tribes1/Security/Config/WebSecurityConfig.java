package com.greenfox.tribes1.Security.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.tribes1.Security.AjaxAuthenticationProvider;
import com.greenfox.tribes1.Security.Filters.AjaxLoginProcessingFilter;
import com.greenfox.tribes1.Security.JWT.Extractor.TokenExtractor;
import com.greenfox.tribes1.Security.JWT.JwtAuthenticationProvider;
import com.greenfox.tribes1.Security.Filters.JwtTokenAuthenticationProcessingFilter;
import com.greenfox.tribes1.Security.RestAuthenticationEntryPoint;
import com.greenfox.tribes1.Security.SkipPathRequestMatcher;
import com.greenfox.tribes1.Security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
  public static final String AUTHENTICATION_URL = "/login";
  public static final String REGISTRATION_URL = "/register";
  public static final String REFRESH_TOKEN_URL = "/token";
  public static final String API_ROOT_URL = "/**";

  @Autowired private UserDetailsServiceImpl userDetailsService;
  @Autowired private RestAuthenticationEntryPoint authenticationEntryPoint;
  @Autowired private AuthenticationSuccessHandler successHandler;
  @Autowired private AuthenticationFailureHandler failureHandler;
  @Autowired private AjaxAuthenticationProvider ajaxAuthenticationProvider;
  @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;
  @Autowired private TokenExtractor tokenExtractor;
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private ObjectMapper objectMapper;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    List<String> permitAllEndpointList = Arrays.asList(
            AUTHENTICATION_URL,
            REFRESH_TOKEN_URL

    );
    http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(AUTHENTICATION_URL, REGISTRATION_URL).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(buildAjaxLoginProcessingFilter(AUTHENTICATION_URL), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(permitAllEndpointList, API_ROOT_URL), UsernamePasswordAuthenticationFilter.class);;
  }

  protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter(String loginEntryPoint) throws Exception {
    AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(loginEntryPoint, successHandler, failureHandler, objectMapper);
    filter.setAuthenticationManager(this.authenticationManager);
    return filter;
  }

  protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter(List<String> pathsToSkip, String pattern) throws Exception {
    SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, pattern);
    JwtTokenAuthenticationProcessingFilter filter
            = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
    filter.setAuthenticationManager(this.authenticationManager);
    return filter;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(ajaxAuthenticationProvider);
    auth.authenticationProvider(jwtAuthenticationProvider);
  }

}
