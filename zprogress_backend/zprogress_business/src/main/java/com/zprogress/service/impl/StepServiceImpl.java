package com.zprogress.service.impl;

import com.zprogress.domain.Goal;
import com.zprogress.domain.Step;
import com.zprogress.domain.exception.GoalNotFoundValidationException;
import com.zprogress.domain.repository.GoalRepository;
import com.zprogress.domain.repository.StepRepository;
import com.zprogress.domain.services.StepService;
import org.springframework.transaction.annotation.Transactional;

public class StepServiceImpl implements StepService {

    private StepRepository stepRepository;
    private GoalRepository goalRepository;

    public StepServiceImpl(StepRepository stepRepository, GoalRepository goalRepository) {
        this.stepRepository = stepRepository;
        this.goalRepository = goalRepository;
    }

    @Transactional
    @Override
    public Step create(Step step, Long goalId) throws GoalNotFoundValidationException {
        Goal goal = goalRepository.getById(goalId);
        if (goal == null) {
            throw new GoalNotFoundValidationException(goalId);
        }
        step.setGoal(goal);
        return stepRepository.create(step);
    }

    @Override
    public Step get(Long id) {
        return null;
    }
}
