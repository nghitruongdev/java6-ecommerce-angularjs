package com.vnco.common.model.user;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends RepresentationModel<User> {
    private String     username;
    private String     password;
    private String     fullName;
    private String     phone;
    private String     email;
    private String     address;
    private String     photo;
    private List<Role> roles = new ArrayList<>();
}
