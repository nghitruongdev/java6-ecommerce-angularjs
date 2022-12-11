package com.vnco.common.util;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Service
public class PathUtil {
    private final ServletContext context;

    @SneakyThrows
    public Path getPath(String folderName) {
        Path dir = Paths.get(context.getRealPath("/files/"), folderName);
        if (Files.notExists(dir)) Files.createDirectories(dir);
        return dir;
    }

    @SneakyThrows
    public Path getPath(String folderName, String fileName) {
        return Paths.get(getPath(folderName).toString(), fileName);
    }
}
