package com.zprogress.controller.goal;

import com.zprogress.controller.ClientContext;
import com.zprogress.domain.Goal;
import com.zprogress.domain.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private ClientContext clientContext;
    private GoalService goalService;

    @GetMapping
    public ResponseEntity<CollectionModel<GoalEntityModel>> goals() {
        var goals = goalService.goals(clientContext.getUsername());
        var body = new CollectionModel(goals
                .stream()
                .map(goal -> new GoalEntityModel(new GoalDTO(goal), true))
                .collect(Collectors.toList()));
        body.add(
                linkTo(methodOn(GoalController.class).goals()).withSelfRel()
                    .andAffordance(afford(methodOn(GoalController.class).postGoal(null)))
        );
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{goalId}")
    public GoalEntityModel getGoal(@PathVariable Long goalId) {
        var goal = goalService.get(goalId);
        return new GoalEntityModel(new GoalDTO(goal), false);
    }

    // TODO don't return body --> put id in location
    @PostMapping
    public ResponseEntity<GoalEntityModel> postGoal(@RequestBody GoalDTO goal) {
//        var newGoal = goal.getContent();
//        newGoal.setUsername(clientContext.getUsername());
//        newGoal = goalService.create(newGoal);
        return new ResponseEntity<>(new GoalEntityModel(new GoalDTO(new Goal()), false), HttpStatus.CREATED);
    }

    // TODO: afford with get goal/1
    @PutMapping("/{goalId}")
    public ResponseEntity<GoalEntityModel> putGoal(@PathVariable Long goalId, @RequestBody GoalDTO goal) {
//        var newGoal = goal.getContent();
//        newGoal.setUsername(clientContext.getUsername());
//        newGoal = goalService.create(newGoal);
        return new ResponseEntity<>(new GoalEntityModel(new GoalDTO(new Goal()), false), HttpStatus.CREATED);
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
