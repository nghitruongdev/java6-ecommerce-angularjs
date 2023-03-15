package com.vnco.cartservice.category;

import com.vnco.common.model.category.Category;
import com.vnco.common.model.category.CategoryWithProductNumberDTO;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    PagedModel<Category> getCategories();
    
    List<CategoryWithProductNumberDTO> getCategoriesWithNumberOfProducts();
}
