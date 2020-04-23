package com.zprogress.controller.goal;

import com.zprogress.controller.step.StepController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class GoalEntityModel extends EntityModel<GoalDTO> {
    Logger logger = LoggerFactory.getLogger(GoalEntityModel.class);

    public GoalEntityModel(GoalDTO goal) {
        super(goal);
        add(linkTo(methodOn(StepController.class).steps(goal.getId())).withRel("steps"));
    }
}
