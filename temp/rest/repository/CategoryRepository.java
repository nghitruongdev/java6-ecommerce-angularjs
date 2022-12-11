package com.fpoly.java6asm.rest.repository;

import com.fpoly.java6asm.rest.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository
        extends JpaRepository<Category, Long> {
}
