package com.vnco.common.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSecurity extends RepresentationModel<UserSecurity> {
    private String     username;
    private String     password;
    private List<Role> roles;
}
