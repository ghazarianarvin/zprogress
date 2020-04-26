package com.zprogress.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/authentication")
    public ResponseEntity<AuthenticationResponseEntityModel> authenticate(HttpServletRequest request) {
        var username = request.getHeader("username");
        var password = request.getHeader("password");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        var jwtToken = jwtTokenService.getToken(username);
        logger.info("generated new token ({}) for {}", jwtToken, username);
        return new ResponseEntity(new AuthenticationResponseEntityModel(jwtToken), HttpStatus.OK);
    }

    @PostMapping(value = "/authentication/logout")
    public ResponseEntity<AuthenticationResponseEntityModel> logout(HttpServletRequest request) {
        var username = request.getHeader("username");
        var jwt = JwtTokenUtil.extractJwtFromRequest(request);

        if (jwt.isPresent() && jwtTokenService.isTokenValid(jwt.get())) {
            username = jwtTokenService.extractUsernameFromToken(jwt.get());
            if (jwtTokenService.discardTokenFor(username)) {
                logger.info("{}'s token was successfully discarded.", username);
                return new ResponseEntity(new AuthenticationResponseEntityModel("Token successfully discarded"), HttpStatus.OK);
            }
        }
        return new ResponseEntity(new AuthenticationResponseEntityModel("No valid JWT token provided"), HttpStatus.BAD_REQUEST);
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
