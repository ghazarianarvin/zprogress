package com.zprogress.security;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class JwtTokenUtil {

    private JwtTokenUtil() {}

    public static Optional<String> extractJwtFromRequest(ServletRequest servletRequest) {
        var httpRequest = (HttpServletRequest) servletRequest;
        return extractJwtFromRequest(httpRequest);
    }

    public static Optional<String> extractJwtFromRequest(HttpServletRequest httpRequest) {
        var authorizationHeader = httpRequest.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var jwt = authorizationHeader.substring(7);
            return Optional.of(jwt);
        }
        return Optional.empty();
    }
}
