package com.zprogress.config;

import com.zprogress.domain.repository.GoalRepository;
import com.zprogress.domain.repository.StepRepository;
import com.zprogress.domain.services.GoalService;
import com.zprogress.domain.services.StepService;
import com.zprogress.reporsitory.GoalRepositoryImpl;
import com.zprogress.reporsitory.StepRepositoryImpl;
import com.zprogress.security.JwtRequestFilter;
import com.zprogress.security.JwtTokenService;
import com.zprogress.security.SecurityConfigurer;
import com.zprogress.security.UserDetailsServiceImpl;
import com.zprogress.service.impl.GoalServiceImpl;
import com.zprogress.service.impl.StepServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class BeanConfiguration {

    @Bean
    public GoalRepository goalRepository(DataSource dataSource) {
        return new GoalRepositoryImpl(dataSource);
    }

    @Bean
    public GoalService goalService(GoalRepository goalRepository) {
        return new GoalServiceImpl(goalRepository);
    }

    @Bean
    public StepRepository stepRepository(DataSource dataSource) {
        return new StepRepositoryImpl(dataSource);
    }

    @Bean
    public StepService stepService(StepRepository stepRepository, GoalRepository goalRepository) {
        return new StepServiceImpl(stepRepository, goalRepository);
    }

    //-------------------------- SECURITY ------------------------------------------------------------------------------
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
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
}
