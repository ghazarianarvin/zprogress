package com.zprogress.controller.goal;

import com.zprogress.controller.ClientContext;
import com.zprogress.domain.Goal;
import com.zprogress.domain.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class GoalController {

    private ClientContext clientContext;
    private GoalService goalService;

    /**
     * TODO
     * 204 no content -> if empty result set
     * 200 accepted
     */
    @GetMapping("/goals")
    public ResponseEntity<CollectionModel<GoalEntityModel>> goals() {
        var goals = goalService.goals(clientContext.getUsername());
        var body = new CollectionModel(goals
                .stream()
                .map(goal -> new GoalEntityModel(goal))
                .collect(Collectors.toList()));
        body.add(linkTo(methodOn(GoalController.class).goals()).withSelfRel()
            .andAffordance(afford(methodOn(GoalController.class).goals(null))) // Post affordance
        );
        return ResponseEntity.ok(body);
    }

    /**
     * TODO
     * 201 created -> if insert
     * 400 bad request
     */
    @PostMapping("/goals")
    public ResponseEntity<GoalEntityModel> goals(@RequestBody EntityModel<Goal> goal) {
        var newGoal = goal.getContent();
        newGoal.setUsername(clientContext.getUsername());
        newGoal = goalService.create(newGoal);
        return new ResponseEntity<>(new GoalEntityModel(newGoal), HttpStatus.CREATED);
    }

    /**
     * TODO
     * 204 no content -> if update
     * 201 created -> if insert
     * 400 bad request
     */
    @PutMapping("/goals/{id}")
    public ResponseEntity<RepresentationModel> goals(@PathVariable Long id, @RequestBody EntityModel<Goal> goal) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * TODO
     * 204 no content -> no result found
     * 200 accepted -> goal in response body
     */
    @GetMapping("/goal/{id}")
    public ResponseEntity<GoalEntityModel> goal(@PathVariable Long id) {
        var goal = goalService.get(id);
        return new ResponseEntity<>(new GoalEntityModel(goal), HttpStatus.ACCEPTED);
    }

    @Autowired
    public GoalService setGoalService(GoalService goalService) {
        return this.goalService = goalService;
    }

    @Autowired
    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
}
