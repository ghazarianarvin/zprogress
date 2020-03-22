package com.zprogress.config;

import com.zprogress.domain.services.GoalService;
import com.zprogress.domain.services.impl.GoalServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Bean
    public GoalService goalService() {
        return new GoalServiceImpl();
    }
}
