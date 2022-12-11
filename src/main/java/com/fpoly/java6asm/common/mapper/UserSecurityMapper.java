package com.fpoly.java6asm.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.userdetails.User;

@Mapper (componentModel = "spring")
public interface UserSecurityMapper {
    @Mapping (target = "username", source = "user.username")
    @Mapping (target = "password", source = "user.password")
    @Mapping (
            target = "roles",
            expression = "java(user.roles.stream().map(Role::getName).toArray(String[]::new))"
    )
    User toUserSecurity(com.vnco.common.model.user.User user);
}
