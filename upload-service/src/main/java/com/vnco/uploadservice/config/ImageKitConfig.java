package com.vnco.uploadservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties (prefix = "imagekit")
@Setter
@Getter
public class ImageKitConfig {
    private String privateKey;
    private String uploadUrl;
}
