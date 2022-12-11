package com.fpoly.java6asm.rest.entity.user;

import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

/**
 * A Projection for the {@link User} entity
 */
@Projection (name = "withRoles", types = User.class)
public interface UserWithRolesProjection {
    String getUsername();
    
    String getFullName();
    
    String getEmail();
    
    String getPhone();
    
    String getAddress();
    
    String getPhoto();
    
    Set<Role> getRoles();
    
    
}
