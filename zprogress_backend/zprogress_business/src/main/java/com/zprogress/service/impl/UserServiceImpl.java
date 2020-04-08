package com.zprogress.service.impl;

import com.zprogress.domain.User;
import com.zprogress.domain.repository.UserRepository;
import com.zprogress.domain.services.UserService;
import org.springframework.util.DigestUtils;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findBy(String name, String password) {
        return userRepository.findBy(name, DigestUtils.md5DigestAsHex(password.getBytes()));
    }

    @Override
    public Optional<User> findBy(String name) {
        return Optional.of(userRepository.findBy(name));
    }
}
