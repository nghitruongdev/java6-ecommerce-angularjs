package com.fpoly.java6asm.product;

import com.vnco.common.model.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl
        implements ProductService {
    //    private final ProductRepository productRepository;
    
    @Override
    public Product getById(Long id) {
        log.info(">>Find product id: " + id);
        //        return productRepository.findById(id)
        //                                .orElseThrow(() -> new RuntimeException("Product Not Found: " + id));
        return null;
    }
    
    @Override
    public List<Product> getAll() {
        log.info(">>Find all products");
        //        return productRepository.findAll();
        return null;
    }
    
    @Override
    public Page<Product> getAll(Pageable pageable) {
        log.info(">>Find all products with page");
//        return productRepository.findAll(pageable);
        return null;
    }
    
    @Override
    public Page<Product> getAllByCategoryId(Long categoryId, Pageable pageable) {
//        log.info(">>Find all products by Category Id: {}", categoryId);
//        return productRepository.findAllByCategoryId(categoryId, pageable);
        return null;
    }
    
    @Override
    public Long getNumberOfProductsByCategory(Long categoryId) {
//        return productRepository.countAllByCategory_Id(categoryId);
        return null;
    }
    
    @Override
    public Page<Product> findByKeyword(String keyword, Pageable pageable) {
//        return productRepository.findByNameLikeIgnoreCase("%" + keyword + "%", pageable);
        return null;
    }
    
    
}
