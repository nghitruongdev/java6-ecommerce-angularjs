//package com.vnco.uploadservice.service;
//
//import com.vnco.uploadservice.ImageRepository;
//import com.vnco.uploadservice.domain.ImageQueue;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//@Qualifier ("local")
//public class LocalImageUploadServiceImpl implements ImageUploadService {
//    @Autowired
//    ImageRepository imageRepository;
//
//    @Override
//    public void upload(ImageQueue request) {
////        String fileName = request.files().get(0).getPath();
////        Image  image    = ImageFactory.getImage(request, fileName);
////        imageRepository.save(image);
////        log.info(">>Saved image to the database: {}", image);
//    }
//}
