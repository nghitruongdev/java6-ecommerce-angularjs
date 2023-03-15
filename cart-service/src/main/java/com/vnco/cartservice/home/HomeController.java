package com.vnco.cartservice.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final DiscoveryClient client;
    
    @RequestMapping ("/")
    public String index() {
        return "forward:/products";
    }
    
    @RequestMapping ("/admin")
    public RedirectView openAdmin(RedirectView view) {
        var adminInstance = client.getInstances("admin");
        if (adminInstance.isEmpty()) {
            return null;
        }
        URI    url  = adminInstance.get(0).getUri();
        String host = adminInstance.get(0).getHost();
        view.setHosts(host);
        view.setUrl(String.format("%s/admin", url.toString()));
        return view;
    }
}
