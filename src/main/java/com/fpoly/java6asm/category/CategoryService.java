package com.fpoly.java6asm.category;

import com.vnco.common.model.category.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    
    Category findById(Long id);
}
