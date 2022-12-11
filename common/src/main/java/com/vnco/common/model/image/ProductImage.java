package com.vnco.common.model.image;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;


@SuperBuilder (toBuilder = true)
@Entity (name = "product")
@AllArgsConstructor
@Getter
@Setter
@Service
public class ProductImage extends Image {
}
