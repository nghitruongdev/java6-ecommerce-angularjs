package com.vnco.common.mapper;

import com.vnco.common.model.category.Category;
import com.vnco.common.model.category.CategoryWithProductNumberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface CategoryWithProductNumberMapper {
    
    @Mapping (
            target = "numberOfProducts", source = "count"
    )
    CategoryWithProductNumberDTO toDto(Category category, Long count);
    
    
}
