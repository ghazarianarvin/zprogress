package com.zprogress.reporsitory;

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

public class StepRepositoryImpl extends AbstractRepository implements StepRepository {

    public static final String INSERT_STEP =
            "INSERT INTO STEP (goal_id, name, importance, startDate, repetitionType, nextReminderDate) " +
            "values (?, ?, ?, ?, ?, ?)";

    public StepRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Step create(Step step) {
        var idHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            var ps = connection
                    .prepareStatement(INSERT_STEP, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, step.getGoal().getId());
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

    private static class StepResultSetHandler implements RowMapper<Step>, ResultSetExtractor<Step> {

        @Override
        public Step mapRow(ResultSet resultSet, int i) throws SQLException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Step extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
//                Step step = new Step();
//                step.setId(resultSet.getLong(1));
//                step.setName(resultSet.getString(2));
//                step.setDescription(resultSet.getString(3));
//                step.setDeadline(resultSet.getDate(4).toLocalDate());
//                return step;
            }
            return null;
        }
    }
}
