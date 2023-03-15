package com.vnco.java6asm.rest.entity.order;

import com.vnco.java6asm.rest.entity.image.ProductImage;
import com.vnco.java6asm.rest.entity.product.Product;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * A Projection for the {@link OrderDetail} entity
 */
@Projection (name = "withProduct", types = OrderDetail.class)
public interface OrderDetailProjection {
    Long getId();
    
    Double getPrice();
    
    Integer getQuantity();
    
    ProductInfo getProduct();
    
    /**
     * A Projection for the {@link Product} entity
     */
    interface ProductInfo {
        
        String getName();
        
        Integer getQuantity();
        
        Double getPrice();
        List<ProductImage> getImages();
    }
}
