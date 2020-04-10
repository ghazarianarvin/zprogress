package com.zprogress.controller.step;

import com.zprogress.domain.Step;
import com.zprogress.domain.exception.GoalNotFoundValidationException;
import com.zprogress.domain.services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Validated
@RestController
public class StepController {

    private StepService stepService;

    @PostMapping("/goals/{goalId}/steps")
    public ResponseEntity<StepEntityModel> addNewStep(@Min(1) @PathVariable Long goalId, @Valid @RequestBody Step step) {
        try {
            var newStep = stepService.create(step, goalId);
            return new ResponseEntity<>(new StepEntityModel(newStep), HttpStatus.CREATED);
        } catch (GoalNotFoundValidationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/goals/{goalId}/steps")
    public ResponseEntity<CollectionModel<StepEntityModel>> allStepsByGoalId(@Min(1) @PathVariable Long goalId) {
        var steps = stepService.getByGoalId(goalId);
        var body = new CollectionModel(steps
                .stream()
                .map(step -> new StepEntityModel(step, goalId))
                .collect(Collectors.toList()));
        body.add(linkTo(methodOn(StepController.class).allStepsByGoalId(goalId)).withSelfRel()
                .andAffordance(afford(methodOn(StepController.class).addNewStep(goalId,null))) // Post affordance
        );
        return ResponseEntity.ok(body);
    }
    /**
     * TODO
     * 204 no content -> if update
     * 201 created -> if insert
     * 400 bad request
     */
    @PutMapping("/goals/{goalId}/steps/{id}")
    public ResponseEntity<RepresentationModel> updateExistingStep(@PathVariable Long goalId, @PathVariable Long id, @RequestBody EntityModel<Step> step) {
        throw new UnsupportedOperationException("not yet implemented");
    }



    @Autowired
    public void setStepService(StepService stepService) {
        this.stepService = stepService;
    }
}
