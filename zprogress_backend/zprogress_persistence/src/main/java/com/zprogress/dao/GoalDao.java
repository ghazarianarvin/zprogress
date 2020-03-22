package com.zprogress.dao;

import com.zprogress.AbstractGoal;
import com.zprogress.entity.GoalEntity;
import com.zprogress.reporsitory.GoalRepository;
import com.zprogress.utils.DomainObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GoalDao implements Dao<AbstractGoal> {

    private GoalRepository goalRepository;

    private DomainObjectMapper domainObjectMapper;

    @Override
    public void save(AbstractGoal goal) {
        this.goalRepository.save(domainObjectMapper.map(goal, GoalEntity.class));
    }

    @Autowired
    public void setGoalRepository(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Autowired
    public void setDomainObjectMapper(DomainObjectMapper domainObjectMapper) {
        this.domainObjectMapper = domainObjectMapper;
    }
}
