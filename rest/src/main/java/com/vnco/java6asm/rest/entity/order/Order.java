package com.vnco.java6asm.rest.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vnco.java6asm.rest.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "orders")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator (name = "order_seq")
    @Column (name = "id", nullable = false)
    private Long              id;
    private Long   createTime;
    @ManyToOne
    @JoinColumn (
            name = "customer_username",
            referencedColumnName = "username",
            nullable = false, insertable = false, updatable = false
    )
    @JsonIgnore
    private User   customer;
    @Column (name = "customer_username")
    private String username;
    private String            fullName;
    private String            phone;
    private String            email;
    private String            address;
    @ToString.Exclude
    @OneToMany (mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    @Builder.Default
    private List<OrderDetail> orderDetails = new ArrayList<>();
    
    public Double getAmount() {
        return orderDetails.stream().mapToDouble(detail -> detail.getPrice() * detail.getQuantity()).sum();
    }
}
