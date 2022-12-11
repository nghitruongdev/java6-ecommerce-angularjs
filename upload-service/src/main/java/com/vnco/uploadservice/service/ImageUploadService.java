package com.vnco.uploadservice.service;

import com.vnco.uploadservice.model.ImageQueue;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface ImageUploadService {
    Queue<ImageQueue> queue = new ConcurrentLinkedQueue<>();
    
    void upload(ImageQueue request);
    
    default void queue(ImageQueue request) {
        queue.offer(request);
    }
    
    @Scheduled (fixedDelay = 5000)
    default void run() {
        while (!queue.isEmpty()) {
            upload(Objects.requireNonNull(queue.poll()));
        }
    }
}
