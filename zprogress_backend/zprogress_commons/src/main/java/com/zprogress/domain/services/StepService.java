package com.zprogress.domain.services;

import com.zprogress.domain.Step;
import com.zprogress.domain.exception.GoalNotFoundValidationException;

public interface StepService {

    Step create(Step step, Long goalId) throws GoalNotFoundValidationException;

    Step get(Long id);
}
