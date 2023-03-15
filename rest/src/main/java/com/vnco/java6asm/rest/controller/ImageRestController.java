package com.vnco.java6asm.rest.controller;

import com.vnco.java6asm.rest.entity.image.ProductImage;
import com.vnco.java6asm.rest.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RepositoryRestController
@RequiredArgsConstructor
@Slf4j
public class ImageRestController {
    private final ImageRepository imageRepository;
    
    @PostMapping ("/images/product/saveAll")
    public ResponseEntity<?> saveAll( @RequestBody List<ProductImage> images) {
        log.info(">> List of images: {}", images);
        imageRepository.saveAll(images);
        return ResponseEntity.ok().build();
    }
}
