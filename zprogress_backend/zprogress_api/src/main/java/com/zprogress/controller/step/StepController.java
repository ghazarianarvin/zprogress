package com.zprogress.controller.step;

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
import javax.validation.constraints.Min;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Validated
@RestController
public class StepController {

    // TODO don't return body --> put id in location
    @PostMapping("/goals/{goalId}/steps")
    public ResponseEntity<StepEntityModel> steps(@Min(1) @PathVariable Long goalId, @Valid @RequestBody Step step) {
        try {
            var newStep = stepService.create(step, goalId);
            return new ResponseEntity<>(new StepEntityModel(new StepDTO(newStep)), HttpStatus.CREATED);
        } catch (GoalNotFoundValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/goals/{goalId}/steps")
    public ResponseEntity<CollectionModel<StepEntityModel>> steps(@Min(1) @PathVariable Long goalId) {
        var steps = stepService.getByGoalId(goalId);
        var body = new CollectionModel(steps
                .stream()
                .map(StepDTO::new)
                .collect(Collectors.toList()));
        body.add(linkTo(methodOn(StepController.class).steps(goalId)).withSelfRel()
                .andAffordance(afford(methodOn(StepController.class).steps(goalId,null))) // Post affordance
        );
        return ResponseEntity.ok(body);
    }

    private StepService stepService;

    @Autowired
    public void setStepService(StepService stepService) {
        this.stepService = stepService;
    }
}
