package com.fpoly.java6asm.rest.controller;

import com.fpoly.java6asm.rest.entity.user.Role;
import com.fpoly.java6asm.rest.entity.user.User;
import com.fpoly.java6asm.rest.repository.RoleRepository;
import com.fpoly.java6asm.rest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RepositoryRestController
@Slf4j
@RequiredArgsConstructor
public class UserRestController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    
    @PutMapping ("/users/{userId}/roles")
    public ResponseEntity<?> updateUserRoles(@PathVariable ("userId") Long userId, @RequestBody Set<Role> roles) {
        log.info("Update roles of user: {}, and roles: {}", userId, roles);
        User      user    = userRepository.findById(userId).get();
        Set<Role> roleSet = new HashSet<>();
        roleRepository.findAll().forEach(roleSet::add);
        Set<Role> newRoleSet = roles.stream()
                                    .map(role -> roleSet.stream()
                                                        .filter(item -> item.getName().equals(role.getName()))
                                                        .findFirst()
                                                        .get())
                                    .collect(Collectors.toSet());
        user.setRoles(newRoleSet);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
