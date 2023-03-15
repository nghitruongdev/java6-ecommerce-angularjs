package com.vnco.cartservice.order;

import com.vnco.common.client.CheckoutRequest;
import com.vnco.common.model.order.Order;

public interface OrderService {
    Order placeOrder(CheckoutRequest request);
    
    Order getById(Long id);
}
