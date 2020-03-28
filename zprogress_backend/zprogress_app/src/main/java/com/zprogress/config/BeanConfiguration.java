package com.zprogress.config;

import com.zprogress.domain.repository.GoalRepository;
import com.zprogress.domain.repository.StepRepository;
import com.zprogress.domain.services.GoalService;
import com.zprogress.domain.services.StepService;
import com.zprogress.reporsitory.GoalRepositoryImpl;
import com.zprogress.reporsitory.StepRepositoryImpl;
import com.zprogress.service.impl.GoalServiceImpl;
import com.zprogress.service.impl.StepServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
