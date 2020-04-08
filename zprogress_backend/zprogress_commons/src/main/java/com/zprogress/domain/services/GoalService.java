package com.zprogress.domain.services;

import com.zprogress.domain.Goal;

import java.util.List;

public interface GoalService {

    List<Goal> goals();

    Goal create(Goal goal);

    Goal get(Long id);

    List<Goal> goals(String username);
}
