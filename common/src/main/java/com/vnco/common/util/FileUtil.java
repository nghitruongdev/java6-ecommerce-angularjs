package com.vnco.common.util;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {
    
    @SneakyThrows
    public static File convertToTempFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        assert originalName != null;
        String ext      = originalName.substring(originalName.lastIndexOf("."));
        Path   tempFile = Files.createTempFile(String.valueOf(new Date().getTime()), ext);
        System.out.println(tempFile.getFileName());
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
        return tempFile.toFile();
    }
    
    public static List<File> convertToTempFile(List<MultipartFile> files) {
        return files.stream().map(FileUtil::convertToTempFile).collect(Collectors.toList());
    }
    
    @SneakyThrows
    public static String base64Encoder(File file) {
        return Base64.getUrlEncoder().encodeToString(Files.readAllBytes(file.toPath()));
        //        return Base64.getUrlEncoder().encodeToString(FileCopyUtils.copyToByteArray(file));
    }
}
