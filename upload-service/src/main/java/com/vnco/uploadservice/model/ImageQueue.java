package com.vnco.uploadservice.model;

import java.io.File;
import java.util.List;
public record ImageQueue(String type, Long clientId, String folder, List<File> files) {
}
