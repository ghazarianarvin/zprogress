package com.zprogress.service.impl;

import com.zprogress.domain.Goal;
import com.zprogress.reporsitory.GoalRepository;
import com.zprogress.service.GoalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoalServiceImpl implements GoalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoalServiceImpl.class);

    private GoalRepository goalRepository;

    @Transactional
    @Override
    public Goal create(Goal goal) {
        return this.goalRepository.create(goal);
    }

    @Autowired
    public void setGoalRepository(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }
}
