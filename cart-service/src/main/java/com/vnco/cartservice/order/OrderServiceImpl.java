package com.vnco.cartservice.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnco.common.client.CheckoutRequest;
import com.vnco.common.model.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
//    private final CheckoutOrderMapper orderMapper;
    private final RestTemplate        rest;
    @Value ("${api.base-path}")
    private       String              baseUrl;
    private final String              baseResource = "orders";
//    private final ObjectMapper        objectMapper;
    
    @Override
    @SneakyThrows
    public Order placeOrder(CheckoutRequest request) {
        log.info(">> Placing a new order: {}",
                 new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request));
        String url   = String.format("%s/%s/saveWithDetails", baseUrl, baseResource);
//        Order  order = orderMapper.toOrderEntity(request);
        //        log.info("Order after mapping: {}", objectMapper.writerWithDefaultPrettyPrinter()
        //        .writeValueAsString(order));
        //        HttpEntity<Order> entity = new HttpEntity<>(order);
        HttpEntity<CheckoutRequest> entity = new HttpEntity<>(request);
        return rest.exchange(url, HttpMethod.POST, entity, Order.class).getBody();
    }
    
    @Override
    public Order getById(Long id) {
        log.info(">>Find order with id: {}", id);
        String url = String.format("%s/%s/%d?projection=detail", baseUrl, baseResource, id);
        return rest.getForObject(url, Order.class);
    }
}
