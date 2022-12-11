package com.fpoly.java6asm.rest.entity.product;

import com.fpoly.java6asm.rest.entity.category.Category;

import java.io.Serializable;

/**
 * A DTO for the {@link Product} entity
 */
public record ProductDto(String name, CategoryDto category)
        implements Serializable {
    
    /**
     * A DTO for the {@link Category} entity
     */
    public record CategoryDto(Long id)
            implements Serializable {
    }
}
