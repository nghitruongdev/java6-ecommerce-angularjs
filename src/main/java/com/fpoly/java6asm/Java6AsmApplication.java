package com.fpoly.java6asm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Slf4j
@EntityScan (basePackages = {"com.vnco.common.model", "com.fpoly.java6asm.rest.entity"})
@Controller
public class Java6AsmApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(Java6AsmApplication.class, args);
    }
    
    @RequestMapping("/")
    public String index() {
        log.info(">> Opening index page");
        return "forward:/products";
    }
}
