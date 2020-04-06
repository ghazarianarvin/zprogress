package com.zprogress.domain.repository;

import java.util.List;

public interface CommonRepository<T> {

    T create(T object);
    void update(T object);
    void delete(T object);
    T getById(Long id);
    List<T> getAll();
}
