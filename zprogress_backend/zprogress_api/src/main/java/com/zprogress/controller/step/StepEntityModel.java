package com.zprogress.controller.step;

import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class StepEntityModel extends EntityModel<StepDTO> {

    public StepEntityModel(StepDTO step, Long goalId, boolean collection) {
        super(step);
        if (collection) {
            add(linkTo(methodOn(StepController.class).getStep(goalId, step.getId())).withSelfRel());
        } else {
            add(linkTo(methodOn(StepController.class).getStep(goalId, step.getId())).withSelfRel()
                    .andAffordance(afford(methodOn(StepController.class).putStep(goalId, step.getId(),null))));
        }
    }
}
