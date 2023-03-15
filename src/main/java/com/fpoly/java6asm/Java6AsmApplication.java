package com.fpoly.java6asm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
//@EntityScan (basePackages = {"com.vnco.common.model", "com.fpoly.java6asm.rest.entity", "com.fpoly"})
@Controller
public class Java6AsmApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(Java6AsmApplication.class, args);
    }
    
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
