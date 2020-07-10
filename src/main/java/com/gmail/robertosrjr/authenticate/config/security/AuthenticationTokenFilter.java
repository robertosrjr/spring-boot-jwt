package com.gmail.robertosrjr.authenticate.config.security;

import com.gmail.robertosrjr.authenticate.domain.model.UserModel;
import com.gmail.robertosrjr.authenticate.domain.service.TokenService;
import com.gmail.robertosrjr.authenticate.domain.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserService userService;

    public AuthenticationTokenFilter(TokenService tokenService, UserService userService) {

        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = this.retrieveToken(httpServletRequest);
        boolean isValid = this.tokenService.isValid(token);
        if (isValid) {

            this.authenticateClient(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void authenticateClient(String token) {

        String idUser = tokenService.getIdUser(token);
        UserModel user = this.userService.findById(idUser);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getProfiles());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String retrieveToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");
        System.out.println(token);
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
