package com.fpoly.java6asm.order;

import com.fpoly.java6asm.common.CheckoutRequest;
import com.fpoly.java6asm.rest.entity.order.Order;

public interface OrderService {
    Order placeOrder(CheckoutRequest request);
    
    Order getById(Long id);
}
