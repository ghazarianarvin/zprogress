package com.zprogress.domain.repository;

import com.zprogress.domain.User;

public interface UserRepository extends CommonRepository<User> {

    User findBy(String name, String password);
    User findBy(String name);

}
