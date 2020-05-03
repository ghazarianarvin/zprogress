package com.zprogress.controller.goal;

import com.zprogress.controller.step.StepController;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class GoalEntityModel extends EntityModel<GoalDTO> {

    public GoalEntityModel(GoalDTO goal, boolean colleciton) {
        super(goal);
        if (colleciton) {
            add(linkTo(methodOn(GoalController.class).getGoal(goal.getId())).withSelfRel());
        } else {
            add(linkTo(methodOn(GoalController.class).getGoal(goal.getId())).withSelfRel()
                            .andAffordance(afford(methodOn(GoalController.class).putGoal(goal.getId(), null))),
                    linkTo(methodOn(StepController.class).steps(goal.getId())).withRel("steps"));
        }
    }
}
