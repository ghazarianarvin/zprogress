package com.zprogress.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Set<UserCredentials> userPassword = new HashSet<>();

    static {
        userPassword.add(new UserCredentials("arvin", "mypass"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPassword.stream()
                .filter(u -> username.equals(u.getUsername()))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}
