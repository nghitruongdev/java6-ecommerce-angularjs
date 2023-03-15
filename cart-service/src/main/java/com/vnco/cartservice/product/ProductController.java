package com.vnco.cartservice.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vnco.cartservice.category.CategoryService;
import com.vnco.cartservice.common.MyPageable;
import com.vnco.common.model.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping ("products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private static class Constant {
        static final String PRODUCT_LIST_PAGE   = "product/list";
        static final String PRODUCT_DETAIL_PAGE = "product/detail";
    }
    
    private final ProductService  productService;
    private final CategoryService categoryService;
    
    @GetMapping
    public String list(Model model, Optional<Integer> size, Optional<Integer> page, Optional<Long> categoryId,
                       Optional<String> keyword
    ) throws JsonProcessingException {
        log.info(">> Search keyword: {}", keyword);
        Long       categoryIdValue = categoryId.orElse(null);
        String     keywordValue    = keyword.orElse(null);
        MyPageable pageable        = getPageable(page, size);
        PagedModel<Product> productPage = categoryIdValue != null ?
                                          productService.getAllByCategoryId(categoryIdValue, pageable) :
                                          keywordValue != null ? productService.findByKeyword(keywordValue, pageable) :
                                          productService.getAll(pageable);
        productService.getAll(pageable);
        model.addAttribute("hasContent", productPage.getContent().size() > 0);
        model.addAttribute("productPage", productPage);
        if (categoryIdValue != null)
            model.addAttribute("category", categoryService.findById(categoryIdValue));
        return Constant.PRODUCT_LIST_PAGE;
    }
    
    @GetMapping ("{id}")
    public String details(@PathVariable ("id") Long id, Model model) {
        Product product = productService.getById(id);
        PagedModel<Product> productsByCategory = productService.getAllByCategoryId(product.getCategory().getId(),
                                                                                   new MyPageable(0, 7));
        List<Product> relevantProducts =
                productsByCategory.getContent().stream()
                                  .dropWhile(product1 -> product1.getId().equals(id)).limit(6).toList();
        model.addAttribute("product", product);
        model.addAttribute("products", relevantProducts);
        return Constant.PRODUCT_DETAIL_PAGE;
    }
    
    private MyPageable getPageable(Optional<Integer> page, Optional<Integer> size) {
        return MyPageable.builder().page(page.orElse(0)).size(size.orElse(12)).build();
    }
}
