package com.zprogress.controller;

import com.zprogress.domain.services.GoalService;
import com.zprogress.dto.GoalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/goal", produces = MediaType.APPLICATION_JSON_VALUE)
public class GoalController {

    private GoalService goalService;

    @PostMapping
    public ResponseEntity<GoalDTO> create(@RequestBody GoalDTO goal) {
//        goal = goalService.create(goal.getContent());
        return new ResponseEntity<GoalDTO>(new GoalDTO(null), HttpStatus.CREATED);
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
