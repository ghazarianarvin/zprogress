package com.zprogress.controller;

import com.zprogress.domain.Step;
import com.zprogress.domain.exception.GoalNotFoundValidationException;
import com.zprogress.domain.services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
public class StepController {

    private StepService stepService;

    @PostMapping("/step/{goalId}")
    public ResponseEntity<?> create(@Valid @RequestBody Step step, @Min(1) @PathVariable Long goalId) {
        try {
            Step newStep = stepService.create(step, goalId);
            return new ResponseEntity<>(new StepEntityModel(newStep), HttpStatus.CREATED);
        } catch (GoalNotFoundValidationException e) {
            return new ResponseEntity<>(ValidationFailedEntityModel.goalNotFound(e.getGoalId()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Autowired
    public void setStepService(StepService stepService) {
        this.stepService = stepService;
    }
}
