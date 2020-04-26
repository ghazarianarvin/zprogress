package com.zprogress.security;

import org.springframework.hateoas.EntityModel;

public class AuthenticationResponseEntityModel extends EntityModel<String> {

    public AuthenticationResponseEntityModel(String jwtToken) {
        super(jwtToken);
    }
}
