package com.vnco.adminservice;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final DiscoveryClient discoveryClient;
    
    @RequestMapping ("/auth/logout")
    public RedirectView logout(RedirectView redirectView) {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("cart");
        String                url          =
                instanceList.isEmpty() ? "" : instanceList.get(0).getUri().toString() + "/auth/logout";
        redirectView.setUrl(url);
        return redirectView;
    }
}
