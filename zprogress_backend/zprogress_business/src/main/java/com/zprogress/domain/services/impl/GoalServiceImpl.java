package com.zprogress.domain.services.impl;

import com.zprogress.domain.objects.AbstractGoal;
import com.zprogress.domain.services.GoalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoalServiceImpl implements GoalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoalServiceImpl.class);

    @Override
    public void create(AbstractGoal goal) {
        LOGGER.info("creating goal object {}", goal.toString());
    }
}
