package com.zprogress.reporsitory;

import com.zprogress.domain.Repetition;
import com.zprogress.domain.Step;
import com.zprogress.domain.repository.StepRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StepRepositoryImpl extends AbstractRepository implements StepRepository {

    private static final String SELECT_BY_GOAL_ID = "SELECT id, goal_id, name, importance, startDate, " +
            "repetitionType, nextReminderDate from Step WHERE goal_id = ?";
    private static final String INSERT_STEP =
            "INSERT INTO STEP (goal_id, name, importance, startDate, repetitionType, nextReminderDate) " +
            "values (?, ?, ?, ?, ?, ?)";

    private static final StepResultSetHandler stepResultSetHandler = new StepResultSetHandler();

    public StepRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Step create(Step step) {
        var idHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var ps = connection
                    .prepareStatement(INSERT_STEP, Statement.RETURN_GENERATED_KEYS);
//            ps.setLong(1, step.getGoal().getId());
            ps.setString(2, step.getName());
            ps.setInt(3, step.getImportance());
            ps.setObject(4, step.getStartDate());
            ps.setString(5, step.getRepetitionType().name());
            ps.setObject(6, step.getNextReminderDate());
            return ps;
        }, idHolder);
        step.setId(idHolder.getKey().longValue());
        return step;
    }

    @Override
    public void update(Step object) {

    }

    @Override
    public void delete(Step object) {

    }

    @Override
    public Step getById(Long id) {
        return null;
    }

    @Override
    public List<Step> getAll() {
        return null;
    }

    @Override
    public List<Step> getByGoalId(Long goalId) {
        return jdbcTemplate.query(SELECT_BY_GOAL_ID, (RowMapper<Step>) stepResultSetHandler, goalId);
    }

    private static class StepResultSetHandler implements RowMapper<Step>, ResultSetExtractor<Step> {

        @Override
        public Step mapRow(ResultSet resultSet, int i) throws SQLException {
            return extractSingleRow(resultSet);
        }

        @Override
        public Step extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
                return extractSingleRow(resultSet);
            }
            return null;
        }

        private Step extractSingleRow(ResultSet resultSet) throws SQLException {
            var step = new Step();
            step.setId(resultSet.getLong(1));
            step.setName(resultSet.getString(3));
            step.setImportance(resultSet.getInt(4));
            step.setStartDate(resultSet.getDate(5).toLocalDate());
            step.setRepetitionType(Repetition.valueOf(resultSet.getString(6)));
            step.setNextReminderDate(resultSet.getDate(7).toLocalDate().atTime(0,0));
            return step;
        }
    }
}
