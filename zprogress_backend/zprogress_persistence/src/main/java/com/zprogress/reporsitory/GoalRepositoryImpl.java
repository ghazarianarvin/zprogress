package com.zprogress.reporsitory;

import com.zprogress.domain.Goal;
import com.zprogress.domain.repository.GoalRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GoalRepositoryImpl extends AbstractRepository implements GoalRepository<Goal> {

    public static final String INSERT_GOAL = "INSERT INTO GOAL (name, description, deadline) values (?, ?, ?)";
    private static final String SELECT_GOAL = "SELECT id, name, description, deadline from GOAL WHERE id = ?";

    private static final GoalResultSetHandler goalResultSetHandler = new GoalResultSetHandler();

    @Override
    public Goal create(Goal goal) {
        var idHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT_GOAL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, goal.getName());
            ps.setString(2, goal.getDescription());
            ps.setObject(3, goal.getDeadline());
            return ps;
        }, idHolder);
        goal.setId(idHolder.getKey().longValue());
        return goal;
    }

    @Override
    public void update(Goal object) {

    }

    @Override
    public void delete(Goal object) {

    }

    @Override
    public Goal getById(Long id) {
        return jdbcTemplate.query(SELECT_GOAL, (ResultSetExtractor<Goal>) goalResultSetHandler, id);
    }


    private static class GoalResultSetHandler implements RowMapper<Goal>, ResultSetExtractor<Goal> {

        @Override
        public Goal mapRow(ResultSet resultSet, int i) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Goal extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
                Goal goal = new Goal();
                goal.setId(resultSet.getLong(1));
                goal.setName(resultSet.getString(2));
                goal.setDescription(resultSet.getString(3));
                goal.setDeadline(resultSet.getDate(4).toLocalDate());
                return goal;
            }
            return null;
        }
    }
}
