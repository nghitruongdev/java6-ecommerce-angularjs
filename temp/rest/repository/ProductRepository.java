package com.fpoly.java6asm.rest.repository;

import com.fpoly.java6asm.rest.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository
        extends JpaRepository<Product, Long> {
    @Query ("select p from Product p where p.category.id = ?1")
    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);
    
    Long countAllByCategory_Id(Long categoryId);
    
    Page<Product> findByNameLikeIgnoreCase(String keyword, Pageable pageable);
}
