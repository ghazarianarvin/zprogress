package com.zprogress.domain.repository;

import com.zprogress.domain.Step;

import java.util.List;

public interface StepRepository extends CommonRepository<Step> {

    List<Step> getByGoalId(Long goalId);
}
