package com.zprogress.config;

import com.zprogress.controller.ClientContext;
import com.zprogress.domain.services.UserService;
import com.zprogress.security.JwtTokenService;
import com.zprogress.security.SecurityConfigurer;
import com.zprogress.security.UserDetailsServiceImpl;
import com.zprogress.security.filter.ClientContextFilter;
import com.zprogress.security.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new UserDetailsServiceImpl(userService);
    }

    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        return new SecurityConfigurer(userDetailsService, jwtRequestFilter);
    }

    @Bean
    public AuthenticationManager authenticationManager(WebSecurityConfigurerAdapter webSecurityConfigurerAdapter) throws Exception {
        return webSecurityConfigurerAdapter.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public JwtTokenService jwtTokenService() {
        return new JwtTokenService();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        return new JwtRequestFilter(jwtTokenService, userDetailsService);
    }

    @Bean
    public ClientContextFilter clientContextFilter(JwtTokenService jwtTokenService) {
        return new ClientContextFilter(jwtTokenService);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ClientContext clientContext() {
        return new ClientContext();
    }

}
