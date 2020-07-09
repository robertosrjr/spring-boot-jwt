package com.gmail.robertosrjr.authenticate.config.security;

import com.gmail.robertosrjr.authenticate.domain.service.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = this.retrieveToken(httpServletRequest);
        boolean isValid = this.tokenService.isValid(token);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public AuthenticationTokenFilter(TokenService tokenService) {

        this.tokenService = tokenService;
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
