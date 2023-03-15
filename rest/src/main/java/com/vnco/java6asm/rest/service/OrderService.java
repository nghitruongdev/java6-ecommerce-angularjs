package com.vnco.java6asm.rest.service;

import com.vnco.common.client.CheckoutRequest;
import com.vnco.java6asm.rest.entity.order.Order;

public interface OrderService {
    Order saveOrder(CheckoutRequest request);
}
