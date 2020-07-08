package com.gmail.robertosrjr.authenticate.domain.model;

import org.springframework.security.core.GrantedAuthority;

public class Profile implements GrantedAuthority {

    private String id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
