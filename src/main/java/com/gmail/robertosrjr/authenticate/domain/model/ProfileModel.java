package com.gmail.robertosrjr.authenticate.domain.model;

import org.springframework.security.core.GrantedAuthority;

public class ProfileModel implements GrantedAuthority {

    public ProfileModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
