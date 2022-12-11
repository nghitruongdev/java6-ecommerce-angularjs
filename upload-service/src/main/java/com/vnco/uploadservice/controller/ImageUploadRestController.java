package com.vnco.uploadservice.controller;

import com.vnco.uploadservice.model.ImageUploadRequest;
import com.vnco.uploadservice.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@CrossOrigin ("*")
@RestController
@RequestMapping
@EnableScheduling
@Slf4j
public class ImageUploadRestController {
    @Autowired
    private ImageUploadService imageService;
    
    @PostMapping ("/rest/images/")
    public ResponseEntity<?> upload(ImageUploadRequest request) {
        log.info("Received files from controllers");
        imageService.queue(request.toImageQueue());
        return ResponseEntity.ok().build();
    }
}
