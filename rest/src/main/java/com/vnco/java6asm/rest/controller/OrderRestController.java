package com.vnco.java6asm.rest.controller;

import com.vnco.common.client.CheckoutRequest;
import com.vnco.java6asm.rest.entity.order.Order;
import com.vnco.java6asm.rest.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestController
@RequiredArgsConstructor
@Slf4j
public class OrderRestController {
    private final OrderService orderService;
    
    @PostMapping ("/orders/saveWithDetails")
    public ResponseEntity<?> saveOrder(@RequestBody CheckoutRequest request) {
        log.info("Receive order request: {}", request);
        //                log.info("Order Details :{}", order.getOrderDetails());
        Order savedOrder = orderService.saveOrder(request);
        return ResponseEntity.ok(savedOrder);
    }
}
