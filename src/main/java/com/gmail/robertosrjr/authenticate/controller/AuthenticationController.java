package com.gmail.robertosrjr.authenticate.controller;

import com.gmail.robertosrjr.authenticate.domain.service.TokenService;
import com.gmail.robertosrjr.authenticate.dto.TokenDto;
import com.gmail.robertosrjr.authenticate.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TokenDto> postAuthenticate(@RequestBody @Valid LoginForm login) {

        UsernamePasswordAuthenticationToken dados = login.convert();
        try {
            Authentication authentication = this.authenticationManager.authenticate(dados);
            String token = this.tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
           return ResponseEntity.badRequest().build();
        }
    }
}
