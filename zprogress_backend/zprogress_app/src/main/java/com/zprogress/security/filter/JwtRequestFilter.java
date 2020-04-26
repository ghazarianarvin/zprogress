package com.zprogress.security.filter;

import com.zprogress.security.JwtTokenService;
import com.zprogress.security.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtRequestFilter extends GenericFilterBean {

    private UserDetailsService userDetailsService;

    private JwtTokenService jwtTokenService;

    public JwtRequestFilter(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Optional<String> jwt = JwtTokenUtil.extractJwtFromRequest(servletRequest);
        if (jwt.isPresent()) {
            if (jwtTokenService.isTokenValid(jwt.get())) {
                var username = jwtTokenService.extractUsernameFromToken(jwt.get());
                var userDetails = userDetailsService.loadUserByUsername(username); //TODO cache this
                var authenticationToken =  new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
