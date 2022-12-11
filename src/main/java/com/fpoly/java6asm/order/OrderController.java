package com.fpoly.java6asm.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("orders")
@RequiredArgsConstructor
public class OrderController {
    private static class Constant {
        private static final String ORDER_DETAIL_PAGE = "order/detail";
    }
    
    private final OrderService orderService;
    
    @GetMapping ("{id}")
    public String detail(@PathVariable ("id") Long id, Model model) {
        model.addAttribute("order", orderService.getById(id));
        return Constant.ORDER_DETAIL_PAGE;
    }
    
    @GetMapping
    public String list() {
        return Constant.ORDER_DETAIL_PAGE;
    }
}
