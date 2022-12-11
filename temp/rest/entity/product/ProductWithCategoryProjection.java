package com.fpoly.java6asm.rest.entity.product;


import com.fpoly.java6asm.rest.entity.category.CategoryWithIdProjection;
import com.vnco.common.model.image.ProductImage;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * A Projection for the {@link Product} entity
 */
@Projection (name = "withCategory", types = Product.class)
public interface ProductWithCategoryProjection {
    Long getId();
    
    String getName();
    
    Integer getQuantity();
    
    Double getPrice();
    
    List<ProductImage> getImages();
    
    Boolean getAvailable();
    
    Long getCreateDate();
    
    CategoryWithIdProjection getCategory();
}
