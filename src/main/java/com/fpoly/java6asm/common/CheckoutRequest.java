package com.fpoly.java6asm.common;

import java.util.List;

public record CheckoutRequest(Customer customer, List<OrderProduct> orderProducts) {
    public record Customer(String username, String fullName, String phone, String email, String address) {
    }
    
    public record OrderProduct(Long id, Double price, Integer quantity) {
    }
}
