package com.vnco.uploadservice.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//@Configuration
@Getter
public class FreeUploadImageConfig {
    @Value ("${freeuploadimage.upload.url}")
    private String uploadUrl;
}
