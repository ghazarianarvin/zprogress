package com.zprogress.domain.services.impl;

import com.zprogress.domain.objects.AbstractGoal;
import com.zprogress.domain.reporsitories.GoalRepository;
import com.zprogress.domain.services.GoalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GoalServiceImpl implements GoalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoalServiceImpl.class);

    private GoalRepository goalRepository;

    @Override
    public void create(AbstractGoal goal) {
        this.goalRepository.save(goal);
        LOGGER.info("creating goal object {}", goal.toString());
    }

    @Autowired
    public void setGoalRepository(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }
}
