package com.zprogress.controller;

import com.zprogress.domain.Goal;
import com.zprogress.dto.GoalDTO;
import com.zprogress.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/goal", produces = "application/hal+json")
public class GoalController {

    private GoalService goalService;

    @PostMapping
    public ResponseEntity<GoalDTO> create(@RequestBody Goal goal) {
        goal = goalService.create(goal);
        return new ResponseEntity<GoalDTO>(new GoalDTO(goal), HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<Goal> get(@PathVariable long id) {
//        return null;
//    }

    @Autowired
    public GoalService setGoalService(GoalService goalService) {
        return this.goalService = goalService;
    }
}
