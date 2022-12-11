package com.vnco.java6asm.rest.entity.user;

import jakarta.persistence.*;
import lombok.*;

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
