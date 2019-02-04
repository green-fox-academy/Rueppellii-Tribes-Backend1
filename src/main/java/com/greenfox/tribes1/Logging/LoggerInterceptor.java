package com.greenfox.tribes1.Logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

  private static Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
 /*   log.info("[preHandle][" + request + "]" + "[" + request.getMethod()
            + "]" + request.getRequestURI());*/
    log.info("Request URI       : {}", request.getRequestURI());
    log.info("Request method    : {}", request.getMethod());
    log.info("Request body      : {}", request.getInputStream());
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    log.info("Response status   : {}", response.getStatus());
  }
}
