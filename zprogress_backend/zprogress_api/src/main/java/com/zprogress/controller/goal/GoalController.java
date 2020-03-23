package com.zprogress.controller.goal;

import com.zprogress.Goal;
import com.zprogress.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/goal", produces = "application/hal+json")
public class GoalController {

    private GoalService goalService;

    @PostMapping
    ResponseEntity create(@RequestBody Goal goal) {
        goal = goalService.create(goal);
        final URI uri = MvcUriComponentsBuilder
                .fromController(getClass()).path("/{id}")
                .buildAndExpand(goal.getId()).toUri();
        return ResponseEntity.created(uri).body(null);
    }

    @Autowired
    public GoalService setGoalService(GoalService goalService) {
        return this.goalService = goalService;
    }
}
