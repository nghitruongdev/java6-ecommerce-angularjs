package com.vnco.java6asm.rest.repository;

import com.vnco.java6asm.rest.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface ProductRepository
        extends JpaRepository<Product, Long> {
    @RestResource(path = "findByCategoryId", rel = "findByCategoryId")
    @Query ("select p from Product p where p.category.id = ?1")
    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);
    @RestResource(path = "countByCategoryId", rel = "countByCategoryId")
    Long countAllByCategory_Id(Long categoryId);
    
    @RestResource(path = "findByName", rel = "findByName")
    Page<Product> findByNameLikeIgnoreCase(String keyword, Pageable pageable);
}
