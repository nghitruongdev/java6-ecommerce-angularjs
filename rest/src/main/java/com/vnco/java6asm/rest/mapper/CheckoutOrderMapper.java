package com.vnco.java6asm.rest.mapper;

import com.vnco.common.client.CheckoutRequest;
import com.vnco.java6asm.rest.entity.order.Order;
import com.vnco.java6asm.rest.entity.order.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;

@Mapper (componentModel = "spring", uses = OrderProductMapper.class, imports = {Date.class})
public
interface CheckoutOrderMapper {
    @Mapping (target = "id", ignore = true)
    @Mapping (target = "createTime", expression = "java(new Date().getTime())")
    @Mapping (target = "customer", ignore = true)
    @Mapping (target = "username", source = "request.customer.username")
    @Mapping (target = "fullName", source = "request.customer.fullName")
    @Mapping (target = "phone", source = "request.customer.phone")
    @Mapping (target = "email", source = "request.customer.email")
    @Mapping (target = "address", source = "request.customer.address")
    @Mapping (target = "orderDetails", source = "request.orderProducts")
    Order toOrderEntity(CheckoutRequest request);

}

@Mapper (componentModel = "spring")
interface OrderProductMapper {
    @Mapping (target = "productId", source = "product.id")
    @Mapping (target = "price", source = "product.price")
    @Mapping (target = "quantity", source = "product.quantity")
    @Mapping (target = "id", ignore = true)
    @Mapping (target = "product", ignore = true)
    @Mapping (target = "order", ignore = true)
    OrderDetail toOrderDetailEntity(CheckoutRequest.OrderProduct product);
}
