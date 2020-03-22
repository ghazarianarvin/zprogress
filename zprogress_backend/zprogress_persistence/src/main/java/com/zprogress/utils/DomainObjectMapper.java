package com.zprogress.utils;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class DomainObjectMapper {
    // thread safe
    private static final Gson gson = new Gson();

    public <R, T> R map(T object, Class<?> clazz) {
        return (R) gson.fromJson(gson.toJson(object), clazz);
    }
}
