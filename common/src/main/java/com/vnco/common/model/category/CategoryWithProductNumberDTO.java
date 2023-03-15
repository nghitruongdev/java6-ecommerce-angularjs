package com.vnco.common.model.category;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
public record CategoryWithProductNumberDTO(Long id, String name, Long numberOfProducts) implements Serializable {
}
