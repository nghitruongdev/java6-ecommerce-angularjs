package com.vnco.uploadservice.repo;

import com.vnco.common.model.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
