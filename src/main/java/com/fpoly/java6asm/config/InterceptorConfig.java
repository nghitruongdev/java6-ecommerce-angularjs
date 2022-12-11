package com.fpoly.java6asm.config;

import com.fpoly.java6asm.interceptor.CategoryInterceptor;
import com.fpoly.java6asm.security.config.SecurityInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final CategoryInterceptor categoryInterceptor;
    private final SecurityInterceptor securityInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(categoryInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/api/**", "/admin/**", "/assets/**", "/h2/**", "/error/**", "/_static/**");
        registry.addInterceptor(securityInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/assets/**", "/h2/**", "/error/**", "/_static/**");
        
    }
}
