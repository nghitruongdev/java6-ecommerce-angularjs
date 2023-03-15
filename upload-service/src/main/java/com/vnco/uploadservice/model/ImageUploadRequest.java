package com.vnco.uploadservice.model;

import com.vnco.common.util.FileUtil;
import jakarta.ws.rs.PathParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ImageUploadRequest(String type, Long clientId, String folder,
                                 @PathParam("files") List<MultipartFile> files) {
    public ImageQueue toImageQueue() {
        return new ImageQueue(type, clientId, folder, FileUtil.convertToTempFile(files));
    }
}
