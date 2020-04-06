package com.zprogress.controller;

import com.zprogress.domain.Goal;
import com.zprogress.domain.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class GoalController {

    private GoalService goalService;

    /**
     * TODO
     * 204 no content -> if empty result set
     * 200 accepted
     */
    @GetMapping("/goals")
    public ResponseEntity<CollectionModel<GoalEntityModel>> goals() {
        var goals = goalService.goals();
        var body = new CollectionModel(goals
                .stream()
                .map(goal -> new GoalEntityModel(goal))
                .collect(Collectors.toList()));
        body.add(linkTo(GoalController.class).slash("movies").withSelfRel()
            .andAffordance(afford(methodOn(GoalController.class).goal(null, null))) // PUT affordance
            .andAffordance(afford(methodOn(GoalController.class).goal((Goal) null))) // Post affordance
        );
        return ResponseEntity.ok(body);
    }

    /**
     * TODO
     * 204 no content -> if update
     * 201 created -> if insert
     * 400 bad request
     */
    @PutMapping("/goal/{id}")
    public ResponseEntity<RepresentationModel> goal(@PathVariable Long id, @RequestBody Goal goal) {
        return (ResponseEntity<RepresentationModel>) ResponseEntity.status(HttpStatus.NO_CONTENT);
    }

    /**
     * TODO
     * 204 no content -> no result found
     * 200 accepted -> goal in response body
     */
    @GetMapping("/goal/{id}")
    public ResponseEntity<GoalEntityModel> goal(@PathVariable Long id) {
        return (ResponseEntity<GoalEntityModel>) ResponseEntity.status(HttpStatus.ACCEPTED);
    }


    /**
     * TODO
     * 201 created -> if insert
     * 400 bad request
     */
    @PostMapping("/goal")
    public ResponseEntity<GoalEntityModel> goal(@RequestBody Goal goal) {
        Goal newGoal = goalService.create(goal);
        return new ResponseEntity<>(new GoalEntityModel(newGoal), HttpStatus.CREATED);
    }


    @Autowired
    public GoalService setGoalService(GoalService goalService) {
        return this.goalService = goalService;
    }
}
