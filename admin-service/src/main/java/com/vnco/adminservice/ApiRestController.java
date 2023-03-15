package com.vnco.adminservice;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiRestController {
    private final RestTemplate template;
    
    @RequestMapping ("/api/**")
    @ResponseBody
    public ResponseEntity<?> exchangeApi(HttpServletRequest request) throws IOException {
        log.info(">> Request url: {}", request.getRequestURL());
        var uriBuilder = UriComponentsBuilder.fromHttpUrl("http://rest" + request.getRequestURI());
        request.getParameterMap().forEach(uriBuilder::queryParam);
        String url    = uriBuilder.toUriString();
        String method = request.getMethod();
        log.info(">> Url Request: {}", url);
        HttpEntity<?> entity  = new HttpEntity<>(request.getInputStream());
        HttpHeaders   headers = new HttpHeaders();
        return template.exchange(url, HttpMethod.valueOf(method), entity, JsonNode.class);
    }
}
