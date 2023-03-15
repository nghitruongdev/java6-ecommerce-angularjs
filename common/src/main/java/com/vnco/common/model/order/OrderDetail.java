package com.vnco.common.model.order;

import com.vnco.common.model.product.Product;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    private Long    id;
    private Double  price;
    private Integer quantity;
    private Product product;
    private Long    productId;
    private Order   order;
}
