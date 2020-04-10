package com.zprogress.security;

import org.springframework.hateoas.EntityModel;

public class JwtTokenEntityModel extends EntityModel<String> {

    public JwtTokenEntityModel(String jwtToken) {
        super(jwtToken);
    }
}
