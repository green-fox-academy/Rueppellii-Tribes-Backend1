package com.greenfox.tribes1.Config;

import com.greenfox.tribes1.Logging.LoggerInterceptor;
import com.greenfox.tribes1.Progression.ProgressionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  List<String> excludeEndpoints = Arrays.asList("/register", "/login");

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoggerInterceptor());
    registry.addInterceptor(new ProgressionInterceptor()).excludePathPatterns(excludeEndpoints);
  }
}
