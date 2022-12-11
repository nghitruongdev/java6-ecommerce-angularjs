package com.fpoly.java6asm.product;

import com.fpoly.java6asm.category.CategoryService;
import com.vnco.common.model.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    ) {
        log.info(">> Search keyword: {}", keyword);
        Long     categoryIdValue = categoryId.orElse(null);
        String   keywordValue    = keyword.orElse(null);
        Pageable pageRequest     = getPageRequest(size, page);
        Page<Product> productPage = categoryIdValue != null ?
                                    productService.getAllByCategoryId(categoryIdValue, pageRequest) :
                                    keywordValue != null ? productService.findByKeyword(keywordValue, pageRequest) :
                                    productService.getAll(pageRequest);
        productService.getAll(pageRequest);
        model.addAttribute("hasContent", productPage.hasContent());
        model.addAttribute("productPage", productPage);
        if (categoryIdValue != null)
            model.addAttribute("category", categoryService.findById(categoryIdValue));
        return Constant.PRODUCT_LIST_PAGE;
    }
    
    @GetMapping ("{id}")
    public String details(@PathVariable ("id") Long id, Model model) {
        Product product = productService.getById(id);
        Page<Product> productsByCategory = productService.getAllByCategoryId(product.getCategory().getId(),
                                                                             PageRequest.of(0, 13));
        List<Product> relevantProducts =
                productsByCategory.stream()
                                  .dropWhile(product1 -> product1.getId().equals(id)).toList();
        model.addAttribute("product", product);
        model.addAttribute("products", relevantProducts);
        return Constant.PRODUCT_DETAIL_PAGE;
    }
    
    private Pageable getPageRequest(Optional<Integer> size, Optional<Integer> page) {
        int DEFAULT_PAGE_SIZE = 12;
        int sizeValue         = size.orElse(DEFAULT_PAGE_SIZE);
        int pageValue         = page.orElse(0);
        return PageRequest.of(pageValue, sizeValue);
    }
    
}
