package com.vnco.uploadservice.model;

import lombok.Builder;

import java.io.File;
import java.util.List;
@Builder
public record ImageQueue(String type, Long clientId, String folder, List<File> files) {
}
