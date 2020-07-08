package com.gmail.robertosrjr.authenticate.form;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public @Data class LoginForm {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(this.username, this.password);
    }
}
