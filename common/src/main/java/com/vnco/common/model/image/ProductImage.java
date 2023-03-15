package com.vnco.common.model.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;


@SuperBuilder (toBuilder = true)
@AllArgsConstructor
@Getter
@Setter
@Service
public class ProductImage extends Image {
}
