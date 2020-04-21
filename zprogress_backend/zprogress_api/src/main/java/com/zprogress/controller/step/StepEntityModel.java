package com.zprogress.controller.step;

import org.springframework.hateoas.EntityModel;

public class StepEntityModel extends EntityModel<StepDTO> {

    public StepEntityModel(StepDTO step) {
        super(step);
    }
}
