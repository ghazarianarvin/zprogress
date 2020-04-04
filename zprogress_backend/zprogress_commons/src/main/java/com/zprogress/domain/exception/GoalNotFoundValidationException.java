package com.zprogress.domain.exception;

public class GoalNotFoundValidationException extends Exception {

    private Long goalId;

    public GoalNotFoundValidationException(Long goalId) {
        super();
        this.goalId = goalId;
    }

    public Long getGoalId() {
        return goalId;
    }
}
