//package com.fpoly.java6asm.cart;
//
//import com.fpoly.java6asm.common.CheckoutRequest;
//import com.fpoly.java6asm.common.mapper.UserCustomerMapper;
//import com.fpoly.java6asm.order.OrderService;
//import com.fpoly.java6asm.user.UserService;
//import com.vnco.common.model.order.Order;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping ("cart")
//@Slf4j
//@RequiredArgsConstructor
//public class CartController {
//    private final OrderService orderService;
//    private final UserService  userService;
//    private final UserCustomerMapper userCustomerMapper;
//
//    @GetMapping
//    public String view() {
//        return "cart/cart";
//    }
//
//    @GetMapping ("checkout")
//    public String checkout(Model model, HttpServletRequest request) {
//        log.info(">>Customer username: {}", request.getRemoteUser());
////        model.addAttribute("customer", userCustomerMapper.toCustomerDTO(userService.getByUsername(request.getRemoteUser())));
//        return "cart/checkout";
//    }
//
//
//    @PostMapping ("checkout")
//    @ResponseBody
//    public ResponseEntity<?> processCheckout(@RequestBody CheckoutRequest request) {
//        log.info(">>Received request from client: {}", request);
//        Order order = orderService.placeOrder(request);
////        log.info(">>Order #{} has been saved to the database", order.getId());
////        return ResponseEntity.status(HttpStatus.CREATED).body(order.getId());
//        return null;
//    }
//}
