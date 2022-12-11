package com.fpoly.java6asm.user;

import com.vnco.common.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    //    private final UserRepository userRepository;
    
    @Override
    public User getByUsername(String username) {
        //        return userRepository.findByUsername(username)
        //                             .orElseThrow(() -> new IllegalArgumentException(
        //                                     "No user was found with username: " + username));
        return null;
    }
}
