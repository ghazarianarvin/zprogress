package com.zprogress.config;

import com.zprogress.domain.Goal;
import com.zprogress.domain.repository.GoalRepository;
import com.zprogress.domain.services.GoalService;
import com.zprogress.reporsitory.GoalRepositoryImpl;
import com.zprogress.service.impl.GoalServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BeanConfiguration {

    @Bean
    public GoalRepository<Goal> goalRepository(DataSource dataSource) {
        return new GoalRepositoryImpl(dataSource);
    }

    @Bean
    public GoalService goalService(GoalRepository<Goal> goalRepository) {
        return new GoalServiceImpl(goalRepository);
    }
}
