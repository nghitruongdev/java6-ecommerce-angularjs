package com.vnco.common.model.user;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
public record CustomerDTO(String username, String fullName, String email, String phone, String address, String photo)
        implements Serializable {
}
