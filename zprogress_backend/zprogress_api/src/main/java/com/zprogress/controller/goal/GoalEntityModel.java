package com.zprogress.controller.goal;

import com.zprogress.controller.step.StepController;
import com.zprogress.domain.Goal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class GoalEntityModel extends EntityModel<Goal> {

    public GoalEntityModel(Goal goal) {
        super(goal);
        add(linkTo(methodOn(GoalController.class).goal(goal.getId())).withSelfRel());
        add(WebMvcLinkBuilder.linkTo(methodOn(StepController.class).create(goal.getId(),null)).withRel("step"));
    }
}