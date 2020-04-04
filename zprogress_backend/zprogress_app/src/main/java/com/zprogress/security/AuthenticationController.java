package com.zprogress.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> authenticate(HttpServletRequest request) {
        var username = request.getParameter("username");
        var password = request.getParameter("password");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        var authenticatedUser = userDetailsService.loadUserByUsername(username); // actually redundant
        var jwtToken = jwtTokenService.createToken(authenticatedUser.getUsername());
        return ResponseEntity.ok(jwtToken);
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenService(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
