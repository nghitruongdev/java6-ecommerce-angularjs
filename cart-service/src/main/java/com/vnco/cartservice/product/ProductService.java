package com.vnco.cartservice.product;

import com.vnco.cartservice.common.MyPageable;
import com.vnco.common.model.product.Product;
import org.springframework.hateoas.PagedModel;

public interface ProductService {
    Product getById(Long id);
    
    PagedModel<Product> getAll();
    
    PagedModel<Product> getAll(MyPageable pageable);
    
    PagedModel<Product> getAllByCategoryId(Long categoryId, MyPageable pageable);
    
    Long getNumberOfProductsByCategory(Long categoryId);
    
    PagedModel<Product> findByKeyword(String keyword, MyPageable pageable);
}
