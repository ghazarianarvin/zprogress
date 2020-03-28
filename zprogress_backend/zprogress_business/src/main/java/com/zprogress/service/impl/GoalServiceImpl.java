package com.zprogress.service.impl;

import com.zprogress.domain.Goal;
import com.zprogress.domain.repository.GoalRepository;
import com.zprogress.domain.services.GoalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class GoalServiceImpl implements GoalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoalServiceImpl.class);

    private GoalRepository<Goal> goalRepository;

    @Autowired
    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Transactional
    @Override
    public Goal create(Goal goal) {
        return this.goalRepository.create(goal);
    }

}
