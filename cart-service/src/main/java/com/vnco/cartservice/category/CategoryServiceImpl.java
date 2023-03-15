package com.vnco.cartservice.category;

import com.vnco.cartservice.product.ProductService;
import com.vnco.common.mapper.CategoryWithProductNumberMapper;
import com.vnco.common.model.category.Category;
import com.vnco.common.model.category.CategoryWithProductNumberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryWithProductNumberMapper productNumberMapper;
    private final ProductService                  productService;
    private final RestTemplate                    rest;
    @Value ("${api.base-path}")
    private       String                          baseUrl;
    private       String                          baseResource = "categories";
    
    @Override
    public PagedModel<Category> getCategories() {
        log.info(">>Find all categories");
        String url = String.format("%s/%s?projection=withId", baseUrl, baseResource);
        ParameterizedTypeReference<PagedModel<Category>> typeReference =
                new ParameterizedTypeReference<PagedModel<Category>>() {
                };
        return rest.exchange(url, HttpMethod.GET, null, typeReference).getBody();
    }
    
    @Override
    public List<CategoryWithProductNumberDTO> getCategoriesWithNumberOfProducts() {
        return getCategories().getContent()
                              .stream()
                              .map(category ->
                                           productNumberMapper
                                                   .toDto(category,
                                                          productService.getNumberOfProductsByCategory(
                                                                  category.getId())))
                              .toList();
    }
    
    @Override
    public Category findById(Long id) {
        String url = String.format("%s/%s/%d?projection=withId", baseUrl, baseResource, id);
        return rest.exchange(url, HttpMethod.GET, null, Category.class).getBody();
    }
}
