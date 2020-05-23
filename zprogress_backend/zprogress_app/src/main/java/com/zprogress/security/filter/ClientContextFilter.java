package com.zprogress.security.filter;

import com.zprogress.controller.ClientContext;
import com.zprogress.security.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ClientContextFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(ClientContextFilter.class);
    
    private JwtTokenService jwtTokenService;
    
    private ClientContext clientContext;

    public ClientContextFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var authorizationHeader = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var jwt = authorizationHeader.substring(7);
            if (jwtTokenService.isTokenValid(jwt)) {
                var username = jwtTokenService.extractUsernameFromToken(jwt);
                clientContext.setUsername(username);
                logger.info("populated client context {}", clientContext.toString());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Autowired
    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
    
}
