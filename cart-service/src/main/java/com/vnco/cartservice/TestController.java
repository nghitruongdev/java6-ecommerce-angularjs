package com.vnco.cartservice;

import com.vnco.cartservice.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/test")
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;
    
    private final DiscoveryClient discoveryClient;
    
    @RequestMapping ("/users")
    public Object userSecurity() {
        return userService.getUserSecurityByUsername("vntruong");
    }
    
    @RequestMapping ("/services")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }
    
    @RequestMapping ("/service-instance/{name}")
    public List<ServiceInstance> getInstance(@PathVariable String name) {
        return discoveryClient.getInstances(name);
    }
}
