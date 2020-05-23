package com.zprogress.config;

import com.zprogress.domain.repository.GoalRepository;
import com.zprogress.domain.repository.StepRepository;
import com.zprogress.domain.repository.UserRepository;
import com.zprogress.domain.services.GoalService;
import com.zprogress.domain.services.StepService;
import com.zprogress.domain.services.UserService;
import com.zprogress.reporsitory.GoalRepositoryImpl;
import com.zprogress.reporsitory.StepRepositoryImpl;
import com.zprogress.reporsitory.UserRepositoryImpl;
import com.zprogress.service.impl.GoalServiceImpl;
import com.zprogress.service.impl.StepServiceImpl;
import com.zprogress.service.impl.UserServiceImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;

import javax.sql.DataSource;

//TODO: split RepositoryConfig, ServiceConfig, DataBaseConfig (JDBC, DATASOURCE, PREPAREDSTATEMENT)
@Configuration
public class BeanConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public GoalRepository goalRepository(JdbcTemplate jdbcTemplate) {
        return new GoalRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public GoalService goalService(GoalRepository goalRepository) {
        return new GoalServiceImpl(goalRepository);
    }

    @Bean
    public StepRepository stepRepository(JdbcTemplate jdbcTemplate) {
        return new StepRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public StepService stepService(StepRepository stepRepository, GoalRepository goalRepository) {
        return new StepServiceImpl(stepRepository, goalRepository);
    }

    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate) {
        return new UserRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public PreparedStatementCreatorFactory PreparedStatementCreatorFactory() {
        return new PreparedStatementCreatorFactory("");
    }

}
