package com.zprogress.controller;

import com.zprogress.domain.Goal;
import com.zprogress.domain.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GoalController {

    private GoalService goalService;

    @PostMapping("/goal")
    public ResponseEntity<?> create(@RequestBody EntityModel<Goal> goal) {
        Goal newGoal = goalService.create(goal.getContent());
        return new ResponseEntity<GoalEntityModel>(new GoalEntityModel(newGoal), HttpStatus.CREATED);
    }

    @GetMapping("/goal/{id}")
    public ResponseEntity<GoalEntityModel> get(@PathVariable long id) {
        return new ResponseEntity<GoalEntityModel>(new GoalEntityModel(goalService.get(id)), HttpStatus.ACCEPTED);
    }

    @Autowired
    public GoalService setGoalService(GoalService goalService) {
        return this.goalService = goalService;
    }
}
