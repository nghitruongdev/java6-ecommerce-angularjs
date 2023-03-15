package com.vnco.common.model.image;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder (toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString (onlyExplicitlyIncluded = true)
public class Image {
    Long id;
    String url;
    Long   clientId;
    JsonNode metadata;
}
