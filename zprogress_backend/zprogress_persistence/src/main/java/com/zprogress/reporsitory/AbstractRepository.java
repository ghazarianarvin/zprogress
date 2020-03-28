package com.zprogress.reporsitory;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public abstract class AbstractRepository {

    protected JdbcTemplate jdbcTemplate;

    protected AbstractRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
