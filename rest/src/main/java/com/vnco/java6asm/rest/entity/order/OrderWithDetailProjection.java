package com.vnco.java6asm.rest.entity.order;

import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection (name = "detail", types = Order.class)
public interface OrderWithDetailProjection {
    Long getId();
    
    Long getCreateTime();
    
    String getUsername();
    
    String getFullName();
    
    String getPhone();
    
    String getEmail();
    
    String getAddress();
    
    Double getAmount();
    
    List<OrderDetailProjection> getOrderDetails();
}
