package com.zprogress.controller;

import com.zprogress.controller.goal.GoalController;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ValidationFailedEntityModel extends EntityModel<ValidationFailedEntityModel> {

    private String message;

    private ValidationFailedEntityModel(String message) {
        this.message = message;
    }

    public static ValidationFailedEntityModel goalNotFound(Long goalId) {
        ValidationFailedEntityModel validationFailedEntityModel = new ValidationFailedEntityModel("Goal with id " + goalId + " not found");
        validationFailedEntityModel.add(linkTo(methodOn(GoalController.class).goals(goalId, null)).withRel("goal"));
        return validationFailedEntityModel;
    }
}
