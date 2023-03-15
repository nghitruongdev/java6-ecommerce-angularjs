package com.vnco.common.model.order;


import com.vnco.common.model.user.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private Long   id;
    private Long   createTime;
    private User   customer;
    private String username;
    private String            fullName;
    private String            phone;
    private String            email;
    private String            address;
    private List<OrderDetail> orderDetails = new ArrayList<>();
   
    public Double getAmount() {
        return orderDetails.stream().mapToDouble(detail -> detail.getPrice() * detail.getQuantity()).sum();
    }
}
