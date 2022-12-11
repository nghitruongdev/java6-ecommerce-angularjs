package com.fpoly.java6asm.rest.entity.user;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString (onlyExplicitlyIncluded = true)
@Table (name = "user_roles")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long   id;
    @ToString.Include
    private String name;
    
    public Role(String name) {
        this.name = name;
    }
}
