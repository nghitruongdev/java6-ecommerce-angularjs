package com.fpoly.java6asm.rest.entity.user;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table (name = "accounts")
@Inheritance (strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator (name = "user_seq")
    @Column (name = "id", nullable = false)
    private Long      id;
    @NaturalId (mutable = true)
    private String    username;
    private String    password;
    private String    fullName;
    private String    email;
    private String    phone;
    private String    address;
    private String    photo;
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();
    
    public UserDetails getUserSecurity() {
        return org.springframework.security.core.userdetails.User
                       .withUsername(getUsername())
                       .password(password)
                       .roles(roles.stream()
                                   .map(Role::getName).toArray(String[]::new))
                       .build();
    }
}
