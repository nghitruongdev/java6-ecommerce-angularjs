package com.vnco.java6asm.rest.config;

import com.vnco.java6asm.rest.entity.order.Order;
import com.vnco.java6asm.rest.entity.product.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Product.class, Order.class);
        cors.addMapping("/**").allowedOriginPatterns("*").allowedMethods("*");
    }
    
    @Bean
    public RepresentationModelProcessor<RepresentationModel<Product>> productProcessor() {
        return new RepresentationModelProcessor<RepresentationModel<Product>>() {
            @Override
            public RepresentationModel<Product> process(RepresentationModel<Product> model) {
                model.add(Link.of("http://localhost:8090/rest/images", "imageUpload"));
                return model;
            }
        };
    }
}
