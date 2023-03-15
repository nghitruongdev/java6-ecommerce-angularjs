package com.vnco.cartservice.cart;

import com.vnco.common.client.CheckoutRequest;
import com.vnco.common.mapper.UserCustomerMapper;
import com.vnco.cartservice.order.OrderService;
import com.vnco.cartservice.user.UserService;
import com.vnco.common.model.order.Order;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("cart")
@Slf4j
@RequiredArgsConstructor
public class CartController {
    private final OrderService       orderService;
    private final UserService        userService;
    private final UserCustomerMapper userCustomerMapper;
    
    @GetMapping
    public String view() {
        return "cart/cart";
    }
    
    @GetMapping ("checkout")
    public String checkout(Model model, HttpServletRequest request) {
        log.info(">>Customer username: {}", request.getRemoteUser());
        model.addAttribute("customer", userCustomerMapper.toCustomerDTO(
                userService.getBasicUserByUsername(request.getRemoteUser())));
        return "cart/checkout";
    }
    
    
    @PostMapping ("checkout")
    @ResponseBody
    public ResponseEntity<?> processCheckout(@RequestBody CheckoutRequest request) {
        log.info(">>Received request from client: {}", request);
        Order order = orderService.placeOrder(request);
        log.info(">>Order #{} has been saved to the database", order.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(order.getId());
    }
}
