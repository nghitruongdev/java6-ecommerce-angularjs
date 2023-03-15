package com.vnco.common.model.product;

import com.vnco.common.model.category.Category;
import com.vnco.common.model.image.ProductImage;
import com.vnco.common.model.order.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long               id;
    private String             name;
    private Integer            quantity;
    private Double             price;
    private Boolean            available;
    private Long               createDate;
    private List<ProductImage> images = new ArrayList<>();
    private Category           category;
    private List<OrderDetail>  orderDetails;
}
