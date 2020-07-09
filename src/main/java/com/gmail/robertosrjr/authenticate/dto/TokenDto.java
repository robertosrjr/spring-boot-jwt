package com.gmail.robertosrjr.authenticate.dto;

import lombok.Data;

public @Data class TokenDto {

    public TokenDto(String token, String type) {

        this.token = token;
        this.type = type;
    }

    private String token;
    private String type;
}
