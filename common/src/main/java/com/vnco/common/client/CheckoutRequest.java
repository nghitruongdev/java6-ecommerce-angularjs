package com.vnco.common.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public record CheckoutRequest(Customer customer, List<OrderProduct> orderProducts) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Customer(String username, String fullName, String phone, String email, String address) {
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record OrderProduct(Long id, Double price, Integer quantity) {
    }
}
