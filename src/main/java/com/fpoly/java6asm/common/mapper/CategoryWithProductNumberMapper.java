package com.fpoly.java6asm.common.mapper;

import com.fpoly.java6asm.rest.entity.category.CategoryWithProductNumberDTO;
import com.fpoly.java6asm.product.ProductService;
import com.fpoly.java6asm.rest.entity.category.Category;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring", imports = {ProductService.class})
public interface CategoryWithProductNumberMapper {
    
    @Mapping (
            target = "numberOfProducts", expression = "java(productService.getNumberOfProductsByCategory(category" +
                                                      ".getId()))"
    )
    CategoryWithProductNumberDTO toDto(Category category, @Context ProductService productService);
}
