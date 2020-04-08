package com.zprogress.domain.repository;

import com.zprogress.domain.Goal;

import java.util.List;

public interface GoalRepository extends CommonRepository<Goal> {
    List<Goal> findByUsername(String username);
}
