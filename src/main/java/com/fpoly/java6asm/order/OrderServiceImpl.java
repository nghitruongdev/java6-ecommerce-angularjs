package com.fpoly.java6asm.order;

import com.fpoly.java6asm.common.CheckoutRequest;
import com.fpoly.java6asm.common.mapper.CheckoutOrderMapper;
import com.fpoly.java6asm.rest.entity.order.Order;
import com.fpoly.java6asm.rest.entity.order.OrderDetail;
import com.fpoly.java6asm.rest.entity.product.Product;
import com.fpoly.java6asm.rest.repository.OrderDetailRepository;
import com.fpoly.java6asm.rest.repository.OrderRepository;
import com.fpoly.java6asm.rest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
//    private final OrderRepository       orderRepository;
//    private final OrderDetailRepository orderDetailRepository;
//    private final CheckoutOrderMapper   orderMapper;
//
//    private final ProductRepository productRepository;
    
    @Override
    public Order placeOrder(CheckoutRequest request) {
        log.info(">>Placing a new order");
        Order         order    = orderMapper.toOrderEntity(request);
        Order         newOrder = orderRepository.save(order);
        List<Product> products     = new ArrayList<>();
        newOrder.getOrderDetails().parallelStream().forEach(detail -> {
            detail.setOrder(newOrder);
            products.add(updateProductQuantity(detail));
        });
        orderDetailRepository.saveAll(newOrder.getOrderDetails());
        productRepository.saveAll(products);
        return newOrder;
    }
    
    private Product updateProductQuantity(OrderDetail detail) {
        Product product =
                productRepository.findById(detail.getProductId()).orElseThrow(() -> new RuntimeException("No " +
                                                                                                         "product " +
                                                                                                         "was found " +
                                                                                                         "with id: " + detail.getProductId()));
        product.setQuantity(product.getQuantity() - detail.getQuantity());
        return product;
    }
    
    @Override
    public Order getById(Long id) {
        log.info(">>Find order with id: {}", id);
        return orderRepository.findById(id).orElse(null);
    }
}
