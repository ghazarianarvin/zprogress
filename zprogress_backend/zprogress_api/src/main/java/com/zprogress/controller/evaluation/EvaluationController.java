package com.zprogress.controller.evaluation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
public class EvaluationController {

    @PostMapping("/steps/{stepId}/evaluation")
    public ResponseEntity<EvaluationEntityModel> addNewEvaluation(@Min(1) @PathVariable Long stepId, @Valid @RequestBody EvaluationDTO step) {
        return null;
    }
}
