package com.vnco.uploadservice.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.vnco.common.model.image.Image;
import com.vnco.common.model.image.ProductImage;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ImageFactory {
    public static Image getImage(ImageQueue request, JsonNode node) {
        log.info(">>CLient id: {}", request.clientId());
        if (request.type().equals("PRODUCT")) {
            return ProductImage.builder()
                               .clientId(Long.parseLong(request.clientId().toString()))
                               .url(node.get("url").asText())
                               .metadata(node)
                               .build();
        }
        return null;
    }
}
