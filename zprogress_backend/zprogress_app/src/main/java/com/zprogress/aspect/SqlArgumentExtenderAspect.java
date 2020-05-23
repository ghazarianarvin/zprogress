package com.zprogress.aspect;

import com.zprogress.controller.ClientContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class SqlArgumentExtenderAspect {

    private static final Logger logger = LoggerFactory.getLogger(SqlArgumentExtenderAspect.class);

    private ClientContext clientContext;

    @Around("execution(* org.springframework.jdbc.core.JdbcTemplate+.query(..)) && args(sql, resultExtractor, sqlArguments)")
    public Object aroundQuery(ProceedingJoinPoint proceedingJoinPoint, String sql, Object resultExtractor, Object... sqlArguments) {
        logger.info("################ sql: {} ################", sql);
        logger.info("################ current user: {} ################", clientContext.getUsername());
        logger.info("################ sql arguments: {} ################", Arrays.toString(sqlArguments));

        try {
            Object[] sqlArgumentsExtended = extendSqlArgumentsBy(sqlArguments, clientContext.getUsername());
            List<Object> methodArgs = Arrays.asList(sql, resultExtractor, sqlArgumentsExtended);
            return proceedingJoinPoint.proceed(methodArgs.toArray());
        } catch (Throwable throwable) {
            logger.debug("something went wrong, exception={}, message={}", throwable, throwable.getMessage());
        }
        return null;
    }

    @Around("execution(* org.springframework.jdbc.core.JdbcTemplate+.query(..)) && target(jdbcTemplate) && args(sql, resultExtractor)")
    public Object aroundQuery(ProceedingJoinPoint proceedingJoinPoint, JdbcTemplate jdbcTemplate, String sql, Object resultExtractor) {
        logger.info("################ sql: {} ################", sql);
        logger.info("################ current user: {} ################", clientContext.getUsername());

        try {
            Class returnType = ((MethodSignature) proceedingJoinPoint.getSignature()).getReturnType();
            logger.info("################ return type: {} ################", returnType);
            if (java.util.List.class.equals(returnType))
                return jdbcTemplate.query(sql, (RowMapper<? extends Object>) resultExtractor, clientContext.getUsername());
            return jdbcTemplate.query(sql, (ResultSetExtractor<? extends Object>) resultExtractor, clientContext.getUsername());
        } catch (Throwable throwable) {
            logger.debug("something went wrong, exception={}, message={}", throwable, throwable.getMessage());
        }
        return null;
    }

    private Object[] extendSqlArgumentsBy(Object[] sqlArguments, Object argument) {
        Object[] sqlArgumentsExtended = new Object[sqlArguments.length + 1];
        for (int i = 0; i < sqlArguments.length; i++) {
            sqlArgumentsExtended[i] = sqlArguments[i];
        }
        sqlArgumentsExtended[sqlArgumentsExtended.length - 1] = argument;
        return sqlArgumentsExtended;
    }

    @Autowired
    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
}
