package com.greenfox.tribes1.Config;

import com.greenfox.tribes1.Logging.LoggerInterceptor;
import com.greenfox.tribes1.Progression.ProgressionInterceptor;
import com.greenfox.tribes1.Progression.ProgressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoggerInterceptor());
    registry.addInterceptor(new ProgressionInterceptor());
  }
}
