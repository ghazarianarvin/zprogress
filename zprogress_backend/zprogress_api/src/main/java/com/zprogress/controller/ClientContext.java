package com.zprogress.controller;

import java.time.LocalDateTime;

/**
 * Holding information about the client
 * request scoped
 */
public class ClientContext {

    private LocalDateTime clientContextCreationTs;
    private String username;

    public ClientContext() {
        this.clientContextCreationTs = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (this.username == null)
            this.username = username;
    }

    @Override
    public String toString() {
        return "ClientContext{" +
                "clientContextCreationTs=" + clientContextCreationTs +
                ", username='" + username + '\'' +
                '}';
    }
}
