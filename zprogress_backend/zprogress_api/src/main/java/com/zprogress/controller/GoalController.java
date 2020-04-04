package com.zprogress.controller;

import com.zprogress.domain.Goal;
import com.zprogress.domain.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GoalController {

    private GoalService goalService;

    @PostMapping("/goal")
    public ResponseEntity<GoalEntityModel> create(@RequestBody Goal goal) {
        Goal newGoal = goalService.create(goal);
        return new ResponseEntity<>(new GoalEntityModel(newGoal), HttpStatus.CREATED);
    }

    @GetMapping("/goal/{id}")
    public ResponseEntity<GoalEntityModel> get(@PathVariable Long id) {
        return new ResponseEntity<>(new GoalEntityModel(goalService.get(id)), HttpStatus.ACCEPTED);
    }

    @Autowired
    public GoalService setGoalService(GoalService goalService) {
        return this.goalService = goalService;
    }
}
