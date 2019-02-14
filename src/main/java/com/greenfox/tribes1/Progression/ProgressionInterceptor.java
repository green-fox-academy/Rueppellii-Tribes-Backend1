package com.greenfox.tribes1.Progression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProgressionInterceptor extends HandlerInterceptorAdapter {

  private ProgressionService progressionService;

  @Autowired
  public ProgressionInterceptor(ProgressionService progressionService) {
    this.progressionService = progressionService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    progressionService.findProgressionsWithExpiredTimestamp_CreateOrUpgradeModelFromThem_DeleteThem();
    return super.preHandle(request, response, handler);
  }
}