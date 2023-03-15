package com.vnco.cartservice.user;

import com.vnco.common.model.user.User;
import com.vnco.common.model.user.UserSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final RestTemplate rest;
    @Value ("${api.base-path}")
    private       String       baseUrl;
    
    @Override
    public UserSecurity getUserSecurityByUsername(String username) {
        String url =
                String.format("%s/users/search/findByUsername?username=%s&projection=withRoles", baseUrl, username);
        return rest.exchange(url, HttpMethod.GET, null, UserSecurity.class).getBody();
    }
    
    @Override
    public User getBasicUserByUsername(String username) {
        String url = String.format("%s/users/search/findByUsername?username=%s", baseUrl, username);
        return rest.exchange(url, HttpMethod.GET, null, User.class).getBody();
    }
}
