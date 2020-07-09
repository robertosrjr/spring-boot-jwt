package com.gmail.robertosrjr.authenticate.domain.service;

import com.gmail.robertosrjr.authenticate.domain.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class TokenService {

    @Value("${auth.jwt.expiration}")
    private String expirate;

    @Value("${auth.jwt.secret}")
    private String sectret;

    public String generateToken(Authentication authentication) {

        UserModel usuario = (UserModel) authentication.getPrincipal();
        Calendar hoje = Calendar.getInstance();
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.HOUR_OF_DAY, Integer.valueOf(expirate));
        return Jwts.builder()
                .setIssuer("Aqui_Tem_Organico_Api")
                .setSubject(usuario.getId())
                .setIssuedAt(hoje.getTime())
                .setExpiration(expiration.getTime())
                .signWith(SignatureAlgorithm.HS256, sectret)
                .compact();
    }

    public Boolean isValid(String token) {

        try {
            Jwts.parser().setSigningKey(this.sectret).parseClaimsJws(token);
            return true;
        } catch (NoClassDefFoundError | Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
