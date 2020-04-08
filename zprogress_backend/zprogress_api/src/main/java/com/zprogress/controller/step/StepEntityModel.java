package com.zprogress.controller.step;

import com.zprogress.domain.Step;
import org.springframework.hateoas.EntityModel;

public class StepEntityModel extends EntityModel<Step> {

    public StepEntityModel(Step step) {
        super(step);
    }
}
