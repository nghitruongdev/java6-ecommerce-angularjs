package com.vnco.java6asm.rest.service;


import com.vnco.java6asm.rest.entity.order.OrderDetail;

import java.util.List;

public interface ProductService {
    
    void updateQuantity(List<OrderDetail> orderDetails);
}
