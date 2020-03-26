package com.zprogress.dto;

import com.zprogress.domain.Goal;
import org.springframework.hateoas.EntityModel;

public class GoalDTO extends EntityModel<Goal> {

    public GoalDTO(Goal goal) {
        super(goal);
//        add(linkTo(methodOn(GoalController.class).get(goal.getId())).withSelfRel());
    }
}
