package com.vnco.cartservice.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityInterceptor implements HandlerInterceptor {
    private final HttpSession session;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler
    ) throws Exception {
        String uri = request.getRequestURI();
        if (!uri.startsWith("/auth")) {
            log.info(">>Previous Uri: {}", uri);
            session.setAttribute("previousUri", uri);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    
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
