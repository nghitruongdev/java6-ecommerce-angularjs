//package com.fpoly.java6asm.user;
//
//import com.vnco.common.model.user.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//    //        private final UserRepository userRepository;
//    private final RestTemplate rest = new RestTemplate();
//
//    @Override
//    public User getByUsername(String username) {
//        //        return userRepository.findByUsername(username)
//        //                             .orElseThrow(() -> new IllegalArgumentException(
//        //                                     "No user was found with username: " + username));
//        String url = String.format("http://localhost:8888/api/v1/users/1?projection=withRoles");
//        return rest.getForObject(url, User.class);
//    }
//}
