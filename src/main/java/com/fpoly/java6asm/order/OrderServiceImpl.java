package com.fpoly.java6asm.order;

import com.fpoly.java6asm.common.CheckoutRequest;
import com.vnco.common.model.order.Order;
import com.vnco.common.model.order.OrderDetail;
import com.vnco.common.model.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
//        Order         order    = orderMapper.toOrderEntity(request);
//        Order         newOrder = orderRepository.save(order);
//        List<Product> products     = new ArrayList<>();
//        newOrder.getOrderDetails().parallelStream().forEach(detail -> {
//            detail.setOrder(newOrder);
//            products.add(updateProductQuantity(detail));
//        });
//        orderDetailRepository.saveAll(newOrder.getOrderDetails());
//        productRepository.saveAll(products);
//        return newOrder;
        return null;
    }
    
    private Product updateProductQuantity(OrderDetail detail) {
//        Product product =
//                productRepository.findById(detail.getProductId()).orElseThrow(() -> new RuntimeException("No " +
//                                                                                                         "product " +
//                                                                                                         "was found " +
//                                                                                                         "with id: " + detail.getProductId()));
//        product.setQuantity(product.getQuantity() - detail.getQuantity());
//        return product;
        return null;
    }
    
    @Override
    public Order getById(Long id) {
        log.info(">>Find order with id: {}", id);
//        return orderRepository.findById(id).orElse(null);
        return null;
    }
}
