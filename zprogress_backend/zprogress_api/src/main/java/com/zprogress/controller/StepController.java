package com.zprogress.controller;

import com.zprogress.domain.Step;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
public class StepController {

    @PostMapping("/step/{goalId}")
    public ResponseEntity<StepEntityModel> create(@Valid @RequestBody Step step, @PathVariable Long goalId) {
        return new ResponseEntity<StepEntityModel>(new StepEntityModel(null), HttpStatus.CREATED);
    }
}
