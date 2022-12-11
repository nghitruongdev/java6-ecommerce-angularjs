package com.vnco.common.model.image;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@SuperBuilder (toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString (onlyExplicitlyIncluded = true)
@Table (name = "images")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public class Image {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    Long id;
    String url;
    Long   clientId;
    @Type (JsonType.class)
    @Column (columnDefinition = "json")
    JsonNode metadata;
}
