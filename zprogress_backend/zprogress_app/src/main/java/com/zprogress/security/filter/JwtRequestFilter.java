package com.zprogress.security.filter;

import com.zprogress.security.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtRequestFilter extends GenericFilterBean {

    Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    private UserDetailsService userDetailsService;

    private JwtTokenService jwtTokenService;

    public JwtRequestFilter(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        var authorizationHeader = httpRequest.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var jwt = authorizationHeader.substring(7);
            if (!jwtTokenService.isTokenValid(jwt)) {
                logger.info("invalid jwt ({}) ", jwt);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            var username = jwtTokenService.extractUsernameFromToken(jwt);
            var userDetails = userDetailsService.loadUserByUsername(username);
            var authenticationToken =  new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
