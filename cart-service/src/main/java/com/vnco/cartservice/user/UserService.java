package com.vnco.cartservice.user;

import com.vnco.common.model.user.User;
import com.vnco.common.model.user.UserSecurity;

public interface UserService {
    UserSecurity getUserSecurityByUsername(String username);
    
    User getBasicUserByUsername(String username);
}
