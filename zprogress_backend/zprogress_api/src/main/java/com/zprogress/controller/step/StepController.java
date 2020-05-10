package com.zprogress.controller.step;

import com.zprogress.controller.goal.GoalDTO;
import com.zprogress.domain.Step;
import com.zprogress.domain.exception.GoalNotFoundValidationException;
import com.zprogress.domain.services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//TODO use clientContext
@Validated
@RestController
public class StepController {

    @GetMapping("/goals/{goalId}/steps")
    public ResponseEntity<CollectionModel<StepEntityModel>> steps(@PathVariable Long goalId) {
        var steps = stepService.getByGoalId(goalId);
        var body = new CollectionModel(steps
                .stream()
                .map(step -> new StepEntityModel(new StepDTO(step), goalId,true))
                .collect(Collectors.toList()));
        body.add(linkTo(methodOn(StepController.class).steps(goalId)).withSelfRel()
                .andAffordance(afford(methodOn(StepController.class).postStep(goalId,null))) // Post affordance
        );
        return ResponseEntity.ok(body);
    }

    @GetMapping("/goals/{goalId}/steps/{stepId}")
    public StepEntityModel getStep(@PathVariable Long goalId, @PathVariable Long stepId) {
        //TODO new step service method with goalId and stepID
        var step = stepService.getByGoalId(goalId).stream().filter(s -> s.getId() == stepId).findFirst();
        if (step.isPresent()) {
            return new StepEntityModel(new StepDTO(step.get()), goalId,false);
        }
        return null;
    }

    // TODO don't return body --> put id in location
    @PostMapping("/goals/{goalId}/steps")
    public ResponseEntity<StepEntityModel> postStep(@PathVariable Long goalId, @Valid @RequestBody Step step) {
        try {
            var newStep = stepService.create(step, goalId);
            return new ResponseEntity<>(new StepEntityModel(new StepDTO(newStep), goalId,false), HttpStatus.CREATED);
        } catch (GoalNotFoundValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/goals/{goalId}/steps/{stepId}")
    public ResponseEntity<StepEntityModel> putStep(@PathVariable Long goalId, @PathVariable Long stepId, @RequestBody GoalDTO goal) {
//        var newGoal = goal.getContent();
//        newGoal.setUsername(clientContext.getUsername());
//        newGoal = goalService.create(newGoal);
        return new ResponseEntity<>(new StepEntityModel(new StepDTO(new Step()), goalId, false), HttpStatus.CREATED);
    }

    private StepService stepService;

    @Autowired
    public void setStepService(StepService stepService) {
        this.stepService = stepService;
    }
}
