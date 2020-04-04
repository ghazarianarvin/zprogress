package com.zprogress.service.impl;

import com.zprogress.domain.Goal;
import com.zprogress.domain.Repetition;
import com.zprogress.domain.Step;
import com.zprogress.domain.exception.GoalNotFoundValidationException;
import com.zprogress.domain.repository.GoalRepository;
import com.zprogress.domain.repository.StepRepository;
import com.zprogress.domain.services.StepService;
import com.zprogress.reminder.ReminderCalculator;
import com.zprogress.reminder.ReminderCalculatorFactory;
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

        var repetition = step.getRepetitionType();
        var startDate = step.getStartDate();
        ReminderCalculator reminderCalculator = getReminderCalculator(repetition);
        step.setNextReminderDate(reminderCalculator.calculateNextReminderDateTime(startDate));

        return stepRepository.create(step);
    }

    private ReminderCalculator getReminderCalculator(Repetition repetition) {
        return ReminderCalculatorFactory.reminderCalculatorFor(repetition);
    }

    @Override
    public Step get(Long id) {
        return null;
    }
}
