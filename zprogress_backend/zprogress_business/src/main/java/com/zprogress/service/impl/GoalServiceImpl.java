package com.zprogress.service.impl;

import com.zprogress.AbstractGoal;
import com.zprogress.dao.GoalDao;
import com.zprogress.service.GoalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl implements GoalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoalServiceImpl.class);

    private GoalDao goalDao;

    @Override
    public void create(AbstractGoal goal) {
        this.goalDao.save(goal);
        LOGGER.info("creating goal object {}", goal.toString());
    }

    @Autowired
    public void setGoalRepository(GoalDao goalDao) {
        this.goalDao = goalDao;
    }
}
