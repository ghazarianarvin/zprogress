package com.zprogress.reporsitory;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractRepository {

    protected JdbcTemplate jdbcTemplate;

    protected AbstractRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
