package com.fpoly.java6asm.rest.entity.category;

import lombok.*;

import jakarta.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "categories")
public class Category {
    @Id
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "category_seq"
    )
    @SequenceGenerator (name = "category_seq")
    @Column (
            name = "id",
            nullable = false
    )
    private Long id;
    private String name;
}
