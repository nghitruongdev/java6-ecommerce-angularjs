package com.fpoly.java6asm.rest.entity.user;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
public record CustomerDTO(String username, String fullName, String email, String phone, String address, String photo)
        implements Serializable {
}
