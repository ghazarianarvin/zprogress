package com.zprogress.controller;

import org.springframework.http.ResponseEntity;

public abstract class AbstractController<T> {

    public static final String JSON = "application/json";

    /**
     * Status Codes:
     * -> 201 ... created
     * -> 204 ... no content, if object already exists
     * @param object to persist
     * @return id of the created object
     */
    abstract ResponseEntity create(T object);
}
