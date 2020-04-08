package com.zprogress.service.impl;

import com.zprogress.domain.Goal;
import com.zprogress.domain.repository.GoalRepository;
import com.zprogress.domain.services.GoalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class GoalServiceImpl implements GoalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoalServiceImpl.class);

    private GoalRepository goalRepository;

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public List<Goal> goals() {
        return goalRepository.getAll();
    }

    @Transactional
    @Override
    public Goal create(Goal goal) {
        return this.goalRepository.create(goal);
    }

    @Override
    public Goal get(Long id) {
        return goalRepository.getById(id);
    }

    @Override
    public List<Goal> goals(String username) {
        return goalRepository.findByUsername(username);
    }

}
