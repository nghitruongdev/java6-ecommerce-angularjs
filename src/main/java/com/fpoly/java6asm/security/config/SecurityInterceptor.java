package com.fpoly.java6asm.security.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class SecurityInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView
    ) throws Exception {
        request.setAttribute("remoteUser", request.getRemoteUser());
        request.setAttribute("principal", request.getUserPrincipal());
        log.info(">> Post handle request: {}, user: {}", request.getRequestURI(), request.getRemoteUser());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
