package com.vnco.uploadservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.vnco.common.model.image.Image;
import com.vnco.uploadservice.config.ImageKitConfig;
import com.vnco.uploadservice.model.ImageFactory;
import com.vnco.uploadservice.model.ImageQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Qualifier ("imagekit")
public class ImageUploadServiceImpl implements ImageUploadService {
    private final RestTemplate   loadBalance;
    private final RestTemplate   template;
    private final ImageKitConfig config;
    
    @Override
    public void upload(ImageQueue queue) {
        log.info(">> Upload image to Imagekit image");
        List<Image> images = new ArrayList<>(queue.files().size());
        queue.files().forEach(file -> {
            Image image = upload(queue, file);
            images.add(image);
        });
        log.info(">> Images before save: {}", images.size());
        saveToDatabase(images);
    }
    
    private Image upload(ImageQueue queue, File file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setBasicAuth(config.getPrivateKey(), "");
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file));
        body.add("fileName", file.getName());
        body.add("folder", queue.folder());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        //
        ResponseEntity<JsonNode> entity = template.postForEntity(config.getUploadUrl(), requestEntity,
                                                                 JsonNode.class);
        log.info(">>Response entity: {}", entity.getHeaders());
        log.info(">>Response entity: {}", entity.getBody());
        //        ObjectNode jsonNode = new ObjectMapper().createObjectNode();
        //        jsonNode.put("fileName", file.getName());
        file.delete();
        return ImageFactory.getImage(queue, entity.getBody());
//                ObjectNode objectNode = new ObjectMapper().createObjectNode();
//                objectNode.put("fileName", file.getName());
//                objectNode.put("filePath", file.getPath());
//                return ImageFactory.getImage(queue, objectNode);
//                return ImageFactory.getImage(queue, null);
        
    }
    
    private void saveToDatabase(List<Image> images) {
        log.info("Images before send: {}", images);
        String            url    = "http://rest/api/v1/images/product/saveAll";
        ResponseEntity<?> result = loadBalance.postForEntity(url, new HttpEntity<>(images), JsonNode.class);
        log.info(">> Saved to database: {}", result);
    }
}
