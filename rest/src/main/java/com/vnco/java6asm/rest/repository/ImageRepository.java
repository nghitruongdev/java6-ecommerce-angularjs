package com.vnco.java6asm.rest.repository;

import com.vnco.java6asm.rest.entity.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
