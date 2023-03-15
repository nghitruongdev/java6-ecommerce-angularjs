package com.vnco.uploadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan (basePackages = {"com.vnco.common.model"})
public class UploadServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadServiceApplication.class, args);
    }
    
    @Bean
    @LoadBalanced
    RestTemplate loadBalance() {
        return new RestTemplate();
    }
    
    @Bean
    RestTemplate template() {
        return new RestTemplate();
    }
}
