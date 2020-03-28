package com.zprogress.domain.repository;

public interface CommonRepository<T> {

    T create(T object);
    void update(T object);
    void delete(T object);
    T getById(Long id);
}
