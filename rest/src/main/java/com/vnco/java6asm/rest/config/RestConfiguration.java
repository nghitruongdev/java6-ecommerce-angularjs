package com.vnco.java6asm.rest.config;

import com.vnco.common.model.category.Category;
import com.vnco.java6asm.rest.entity.order.Order;
import com.vnco.java6asm.rest.entity.product.Product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestConfiguration implements RepositoryRestConfigurer {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    private final DiscoveryClient client;
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Product.class, Order.class, Category.class);
        cors.addMapping("/**").allowedOriginPatterns("*").allowedMethods("*");
    }
    
    @Bean
    public RepresentationModelProcessor<?> productProcessor() {
        return new RepresentationModelProcessor<>() {
            @Override
            public RepresentationModel<?> process(RepresentationModel<?> model) {
                String url = getUploadService();
                if (!StringUtils.isBlank(url))
                    model.add(Link.of(getUploadService(), "imageUpload"));
                return model;
            }
        };
    }
    
    public String getUploadService() {
        List<ServiceInstance> instanceList = client.getInstances("upload");
        return instanceList.isEmpty() ? "" : instanceList.get(0).getUri().toString() + "/rest/images";
    }
}
