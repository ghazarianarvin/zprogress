package com.zprogress.domain.services;

import com.zprogress.domain.Step;
import com.zprogress.domain.exception.GoalNotFoundValidationException;

import java.util.List;

public interface StepService {

    Step create(Step step, Long goalId) throws GoalNotFoundValidationException;

    List<Step> getByGoalId(Long goalId);
}
