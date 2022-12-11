package com.vnco.java6asm.rest.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vnco.java6asm.rest.entity.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "order_detail_seq")
    @SequenceGenerator (name = "order_detail_seq")
    @Column (name = "id", nullable = false)
    private Long    id;
    private Double  price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn (name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;
    @Column (name = "product_id")
    @JsonIgnore
    private Long    productId;
    @ManyToOne
    @JoinColumn (name = "order_id")
    @JsonIgnore
    private Order   order;
}
