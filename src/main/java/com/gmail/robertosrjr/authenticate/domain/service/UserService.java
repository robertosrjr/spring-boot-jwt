package com.gmail.robertosrjr.authenticate.domain.service;

import com.gmail.robertosrjr.authenticate.domain.model.UserModel;
import com.gmail.robertosrjr.authenticate.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    public UserModel findById(String id) {

        Optional<UserModel> user = this.repository.findById(id);
        if (user.isPresent())
            return user.get();

        return null;
    }
}
