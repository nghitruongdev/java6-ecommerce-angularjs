package com.vnco.common.model.user;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String     username;
    private String     password;
    private List<Role> roles = new ArrayList<>();
}
