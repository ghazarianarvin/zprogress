package com.zprogress.reporsitory;

import com.zprogress.domain.Goal;
import com.zprogress.domain.repository.GoalRepository;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class GoalRepositoryImpl extends AbstractRepository implements GoalRepository<Goal> {

    public static final String INSERT_GOAL = "INSERT INTO GOAL (name, description, deadline) values (?, ?, ?)";

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
}
