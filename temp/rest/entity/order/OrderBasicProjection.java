package com.fpoly.java6asm.rest.entity.order;

import org.springframework.data.rest.core.config.Projection;

@Projection (name = "basic", types = Order.class)
public interface OrderBasicProjection {
    Long getId();
    
    Long getCreateTime();
    
    String getUsername();
    
    String getFullName();
    
    String getPhone();
    
    String getEmail();
    
    String getAddress();
    
    Double getAmount();
}
