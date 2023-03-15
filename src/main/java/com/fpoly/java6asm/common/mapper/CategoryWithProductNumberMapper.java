package com.fpoly.java6asm.common.mapper;

import com.fpoly.java6asm.product.ProductService;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring", imports = {ProductService.class})
public interface CategoryWithProductNumberMapper {
    
//    @Mapping (
//            target = "numberOfProducts", expression = "java(productService.getNumberOfProductsByCategory(category" +
//                                                      ".getId()))"
//    )
//    CategoryWithProductNumberDTO toDto(Category category, @Context ProductService productService);
}
