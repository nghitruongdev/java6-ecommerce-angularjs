package com.vnco.java6asm.rest.entity.category;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "withId", types = Category.class)
public interface CategoryWithIdProjection {
    Long getId();
    String getName();
}
