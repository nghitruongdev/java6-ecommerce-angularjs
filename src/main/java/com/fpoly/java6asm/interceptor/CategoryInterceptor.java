package com.fpoly.java6asm.interceptor;

import com.fpoly.java6asm.category.CategoryService;
import com.fpoly.java6asm.common.mapper.CategoryWithProductNumberMapper;
import com.fpoly.java6asm.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryInterceptor implements HandlerInterceptor {
    private final CategoryService                 categoryService;
    private final ProductService                  productService;
    private final CategoryWithProductNumberMapper categoryMapper;
    private final HttpSession                     session;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler
    ) throws Exception {
        String uri = request.getRequestURI();
        if (!uri.startsWith("/auth")) {
            session.setAttribute("previousUri", uri);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView
    ) throws Exception {
        log.info(">> Url: {}", request.getRequestURI());
                request.setAttribute("categories", categoryService.getCategories().stream().map(
                        category -> categoryMapper.toDto(category, productService)
                ).collect(Collectors.toList()));
    }
}
