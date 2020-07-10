package com.gmail.robertosrjr.authenticate.domain.repository;

import com.gmail.robertosrjr.authenticate.domain.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    public Optional<UserModel> findById(String id) {

        UserModel user = new UserModel("1", "robertosrjr@gmail.com", "$2a$10$S1.9AOGBeZIGdsAv6zdNUucNYEgJt2866bpI4you2gbgbRDTKxUqy");
        Optional<UserModel> opUser = Optional.of(user);
        return opUser;
    }
}
