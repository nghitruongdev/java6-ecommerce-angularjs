package com.vnco.uploadservice.model;

import com.vnco.common.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ImageUploadRequest(String type, Long clientId, String folder, List<MultipartFile> files) {
    public ImageQueue toImageQueue() {
        return ImageQueue.builder()
                         .type(type)
                         .clientId(clientId)
                         .folder(folder)
                         .files(FileUtil.convertToTempFile(files)).build();
    }
}
