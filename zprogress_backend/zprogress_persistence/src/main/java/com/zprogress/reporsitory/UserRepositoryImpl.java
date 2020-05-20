package com.zprogress.reporsitory;

import com.zprogress.domain.User;
import com.zprogress.domain.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

    private static final String SELECT_USER_NAME_PASSWORD = "SELECT name, password from user where name = ?, password = ?";
    private static final String SELECT_USER_NAME = "SELECT name, password from user where name = ?";
    private static final UserResultSetHandler userResultSetHandler = new UserResultSetHandler();

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public User findBy(String name, String password) {
        return jdbcTemplate.queryForObject(SELECT_USER_NAME_PASSWORD, userResultSetHandler, name, password);
    }

    @Override
    public User findBy(String name) {
        return jdbcTemplate.queryForObject(SELECT_USER_NAME, userResultSetHandler, name);
    }

    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public void update(User object) {

    }

    @Override
    public void delete(User object) {

    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    private static class UserResultSetHandler implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return extractSingleRow(resultSet);
        }

        private User extractSingleRow(ResultSet resultSet) throws SQLException {
            User user = new User();
            user.setName(resultSet.getString(1));
            user.setPassword(resultSet.getString(2));
            return user;
        }
    }

}
