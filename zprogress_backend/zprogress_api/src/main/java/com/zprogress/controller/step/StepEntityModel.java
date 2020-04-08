package com.zprogress.controller.step;

import com.zprogress.domain.Step;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class StepEntityModel extends EntityModel<Step> {

    public StepEntityModel(Step step) {
        this(step, step.getGoal().getId());
    }

    public StepEntityModel(Step step, Long goalId) {
        super(step);
        add(
                linkTo(methodOn(StepController.class).allStepsByGoalId(goalId)).withSelfRel()
                        .andAffordance(afford(methodOn(StepController.class).updateExistingStep(goalId, step.getId(), null)))
        );
    }
}
