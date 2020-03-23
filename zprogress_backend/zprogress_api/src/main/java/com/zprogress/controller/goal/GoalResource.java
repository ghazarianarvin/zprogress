package com.zprogress.controller.goal;

import com.zprogress.Goal;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class GoalResource extends RepresentationModel<GoalResource> {

    private Goal goal;

    public GoalResource(final Goal goal) {
            this.goal = goal;
            final long id = goal.getId();
            add(linkTo(GoalController.class).withRel("goal"));
    }
}
