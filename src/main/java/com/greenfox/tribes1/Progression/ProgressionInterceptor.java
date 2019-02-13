package com.greenfox.tribes1.Progression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProgressionInterceptor extends HandlerInterceptorAdapter {
  @Autowired
  private ProgressionService progressionService;

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//    progressionService.checkConstruction();
    super.afterCompletion(request, response, handler, ex);
  }
}
