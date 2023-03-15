package com.vnco.java6asm.rest.entity.product;

import com.vnco.java6asm.rest.entity.category.Category;
import com.vnco.java6asm.rest.entity.image.ProductImage;
import com.vnco.java6asm.rest.entity.order.OrderDetail;
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
@Table (name = "products")
public class Product {
    @Id
    //    @GeneratedValue (
    //            strategy = GenerationType.SEQUENCE,
    //            generator = "product_seq"
    //    )
    //    @SequenceGenerator (name = "product_seq")
    //    @Column (
    //            name = "id",
    //            nullable = false
    //    )
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long               id;
    private String             name;
    private Integer            quantity;
    private Double             price;
    private Boolean            available;
    private Long               createDate;
    @OneToMany (mappedBy = "clientId", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ProductImage> images = new ArrayList<>();
    @Column(name = "category_id", nullable = false, insertable = true, updatable = true)
    private Long               categoryId;
    @ManyToOne
    @JoinColumn (
            name = "category_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private Category           category;
    @OneToMany (mappedBy = "product")
    @ToString.Exclude
    private List<OrderDetail>  orderDetails;
    
    public void addImage(ProductImage image) {
        image.setClientId(this.getId());
        images.add(image);
    }
}
