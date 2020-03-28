package com.zprogress.controller;

import com.zprogress.domain.Goal;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class GoalEntityModel extends EntityModel<Goal> {

    public GoalEntityModel(Goal goal) {
        super(goal);
        add(linkTo(methodOn(GoalController.class).get(goal.getId())).withSelfRel());
        add(linkTo(methodOn(StepController.class).create(null, goal.getId())).withRel("step"));
    }
}
