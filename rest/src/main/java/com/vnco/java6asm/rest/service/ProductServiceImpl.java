package com.vnco.java6asm.rest.service;

import com.vnco.java6asm.rest.entity.order.OrderDetail;
import com.vnco.java6asm.rest.entity.product.Product;
import com.vnco.java6asm.rest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    
    @Override
    public void updateQuantity(List<OrderDetail> orderDetails) {
        List<Product> products = orderDetails.parallelStream().map(this::updateProductQuantity).toList();
        repository.saveAll(products.stream().filter(Objects::nonNull).toList());
    }
    
    private Product updateProductQuantity(OrderDetail detail) {
        try {
            Product product = repository.findById(detail.getProductId())
                                        .orElseThrow(() -> new RuntimeException(
                                                "Product Id not found: " + detail.getProductId()));
            product.setQuantity(product.getQuantity() - detail.getQuantity());
            log.debug(">> Update product quantity {} success", detail.getProductId());
            return product;
        } catch (Exception e) {
            log.error(">> Error in updating product quantity: " + e.getMessage());
            return null;
        }
    }
}
