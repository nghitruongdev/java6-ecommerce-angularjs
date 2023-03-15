package com.vnco.cartservice.product;

import com.vnco.cartservice.common.MyPageable;
import com.vnco.common.model.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final RestTemplate rest;
    @Value ("${api.base-path}")
    private       String       baseUrl;
    private       String       baseResource = "products";
    
    @Override
    public Product getById(Long id) {
        log.info(">>Find product id: " + id);
        String url = String.format("%s/%s/%d?projection=withCategory", baseUrl, baseResource, id);
        return rest.exchange(url, HttpMethod.GET, null, Product.class).getBody();
    }
    
    @Override
    public PagedModel<Product> getAll() {
        log.info(">>Find all products");
        String url = String.format("%s/%s", baseUrl, baseResource);
        ParameterizedTypeReference<PagedModel<Product>> typeReference =
                new ParameterizedTypeReference<PagedModel<Product>>() {
                };
        return rest.exchange(url, HttpMethod.GET, null, typeReference).getBody();
    }
    
    @Override
    public PagedModel<Product> getAll(MyPageable pageable) {
        log.info(">>Find all products with page");
        String url =
                String.format("%s/%s?page=%d&size=%d", baseUrl, baseResource, pageable.getPage(), pageable.getSize());
        ParameterizedTypeReference<PagedModel<Product>> typeReference =
                new ParameterizedTypeReference<PagedModel<Product>>() {
                };
        return rest.exchange(url, HttpMethod.GET, null, typeReference).getBody();
    }
    
    @Override
    public PagedModel<Product> getAllByCategoryId(Long categoryId, MyPageable pageable) {
        String url =
                String.format("%s/%s/search/findByCategoryId?page=%d&size=%d&categoryId=%d", baseUrl, baseResource,
                              pageable.getPage(), pageable.getSize(), categoryId);
        ParameterizedTypeReference<PagedModel<Product>> typeReference =
                new ParameterizedTypeReference<PagedModel<Product>>() {
                };
        return rest.exchange(url, HttpMethod.GET, null, typeReference).getBody();
    }
    
    @Override
    public PagedModel<Product> findByKeyword(String keyword, MyPageable pageable) {
        String url =
                String.format("%s/%s/search/findByCategoryId?page=%d&size=%d&keyword=%s", baseUrl, baseResource,
                              pageable.getPage(), pageable.getSize(), keyword);
        ParameterizedTypeReference<PagedModel<Product>> typeReference =
                new ParameterizedTypeReference<PagedModel<Product>>() {
                };
        return rest.exchange(url, HttpMethod.GET, null, typeReference).getBody();
    }
    
    @Override
    public Long getNumberOfProductsByCategory(Long categoryId) {
        String url =
                String.format("%s/%s/search/countByCategoryId?categoryId=%d", baseUrl, baseResource, categoryId);
        return rest.exchange(url, HttpMethod.GET, null, Long.class).getBody();
    }
    
}
