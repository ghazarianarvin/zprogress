package com.zprogress.controller;

import com.zprogress.domain.Goal;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class GoalEntityModel extends EntityModel<Goal> {

    public GoalEntityModel(Goal goal) {
        super(goal);
        add(
                linkTo(methodOn(GoalController.class).get(goal.getId())).withRel("zprogress:goal")
                .andAffordance(afford(methodOn(GoalController.class).get(null)))
        );
        add(
                linkTo(methodOn(StepController.class).create(null, goal.getId())).withRel("step")
                .andAffordance(afford(methodOn(StepController.class).create(null, null)))
        );
    }
}
