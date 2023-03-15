package com.vnco.java6asm.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication (
        exclude = {
                SecurityAutoConfiguration.class
        }
)
@EntityScan (basePackages = {"com.vnco.common.model", "com.vnco.java6asm.rest.entity"})
@EnableDiscoveryClient
public class RestServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }
}
