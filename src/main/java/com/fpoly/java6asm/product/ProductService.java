package com.fpoly.java6asm.product;

import com.vnco.common.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product getById(Long id);
    
    List<Product> getAll();
    
    Page<Product> getAll(Pageable pageable);
    
    Page<Product> getAllByCategoryId(Long categoryId, Pageable pageable);
    
    Long getNumberOfProductsByCategory(Long categoryId);
    
    Page<Product> findByKeyword(String keyword, Pageable pageable);
}
