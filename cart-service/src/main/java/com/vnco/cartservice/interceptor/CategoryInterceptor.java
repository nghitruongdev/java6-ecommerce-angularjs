package com.vnco.cartservice.interceptor;

import com.vnco.cartservice.category.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryInterceptor implements HandlerInterceptor {
    private final CategoryService categoryService;
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView
    ) throws Exception {
        log.info(">> Url: {}", request.getRequestURI());
        request.setAttribute("categories", categoryService.getCategoriesWithNumberOfProducts());
    }
}
