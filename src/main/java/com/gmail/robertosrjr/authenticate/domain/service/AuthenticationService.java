package com.gmail.robertosrjr.authenticate.domain.service;

import com.gmail.robertosrjr.authenticate.domain.model.UserModel;
import com.gmail.robertosrjr.authenticate.domain.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AuthenticationRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<UserModel> user = this.repository.findByUserName(s);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("Usuario não encontrado.");
    }
}
