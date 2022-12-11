package com.fpoly.java6asm.category;

import com.fpoly.java6asm.rest.repository.CategoryRepository;
import com.fpoly.java6asm.rest.entity.category.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    
    @Override
    public List<Category> getCategories() {
        log.info(">>Find all categories");
        return categoryRepository.findAll();
    }
    
    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
