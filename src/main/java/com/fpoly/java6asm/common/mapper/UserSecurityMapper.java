package com.fpoly.java6asm.common.mapper;

import com.vnco.common.model.user.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityMapper {
    //    @Mapping (target = "username", source = "user.username")
    //    @Mapping (target = "password", source = "user.password")
    //    @Mapping (
    //            target = "roles",
    //            expression = "java(user.roles.stream().map(Role::getName).toArray(String[]::new))"
    //    )
    public UserDetails toUserSecurity(com.vnco.common.model.user.User user) {
        return User
                       .withUsername(user.getUsername())
                       .password(user.getPassword())
                       .roles(user.getRoles()
                                   .stream()
                                   .map(Role::getName)
                                   .toArray(String[]::new))
                       .build();
    }
}
