package com.zprogress.domain.services;

import com.zprogress.domain.User;

import java.util.Optional;

public interface UserService {

    User findBy(String name, String password);
    Optional<User> findBy(String name);

}
