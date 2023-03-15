package com.vnco.java6asm.rest.service;

import com.vnco.common.client.CheckoutRequest;
import com.vnco.java6asm.rest.entity.order.Order;
import com.vnco.java6asm.rest.mapper.CheckoutOrderMapper;
import com.vnco.java6asm.rest.repository.OrderDetailRepository;
import com.vnco.java6asm.rest.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository       orderRepository;
    private final OrderDetailRepository detailRepository;
    
    private final ProductService      productService;
    private final CheckoutOrderMapper mapper;
    
    @Override
    public Order saveOrder(CheckoutRequest request) {
        log.debug(">> Rest Service saving order to the database: {}", request);
        Order order = mapper.toOrderEntity(request);
        log.info(">> After mapping: {}", order.getOrderDetails());
        Order savedOrder = orderRepository.save(order);
        detailRepository.saveAll(
                order.getOrderDetails()
                     .stream()
                     .peek(detail -> detail.setOrder(savedOrder))
                     .collect(Collectors.toList())
        );
        productService.updateQuantity(order.getOrderDetails());
        return savedOrder;
    }
}
