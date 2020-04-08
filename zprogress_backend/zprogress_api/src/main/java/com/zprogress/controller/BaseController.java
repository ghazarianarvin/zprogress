package com.zprogress.controller;

import com.zprogress.controller.goal.GoalController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BaseController {

    @GetMapping("/base")
    public ResponseEntity<RepresentationModel> base() {
        RepresentationModel resourceSupport = new RepresentationModel();

        resourceSupport.add(
                linkTo(methodOn(BaseController.class).base()).withSelfRel(),
                linkTo(methodOn(GoalController.class).goals()).withRel("Goal"));

        return ResponseEntity.ok(resourceSupport);
    }
}
