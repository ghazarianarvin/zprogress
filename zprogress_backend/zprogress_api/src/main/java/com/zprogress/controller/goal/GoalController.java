package com.zprogress.controller.goal;

import com.zprogress.controller.ClientContext;
import com.zprogress.domain.Goal;
import com.zprogress.domain.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.alps.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mediatype.PropertyUtils.getExposedProperties;
import static org.springframework.hateoas.mediatype.alps.Alps.doc;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private ClientContext clientContext;
    private GoalService goalService;

    @GetMapping
    public ResponseEntity<CollectionModel<GoalEntityModel>> goals() {
        var goals = goalService.goals(clientContext.getUsername());
        var body = new CollectionModel(goals
                .stream()
                .map(goal -> new GoalEntityModel(new GoalDTO(goal)))
                .collect(Collectors.toList()));
        body.add(
                linkTo(GoalController.class).slash("profile").withRel("profiles"),
                linkTo(methodOn(GoalController.class).goals()).withSelfRel()
                    .andAffordance(afford(methodOn(GoalController.class).goals(null)))
        );
        return ResponseEntity.ok(body);
    }

    // TODO don't return body --> put id in location
    @PostMapping
    public ResponseEntity<GoalEntityModel> goals(@RequestBody GoalDTO goal) {
//        var newGoal = goal.getContent();
//        newGoal.setUsername(clientContext.getUsername());
//        newGoal = goalService.create(newGoal);
        return new ResponseEntity<>(new GoalEntityModel(new GoalDTO(new Goal())), HttpStatus.CREATED);
    }


    @GetMapping(value = "/profile", produces = MediaTypes.ALPS_JSON_VALUE)
    public Alps profile() {

        return Alps.alps() //
                .doc(doc() //
                        .href("https://example.org/samples/full/doc.html") //
                        .value("value goes here") //
                        .format(Format.TEXT) //
                        .build()) //
                .descriptor(getExposedProperties(GoalDTO.class).stream() //
                        .map(property -> Descriptor.builder() //
                                .id("class field [" + property.getName() + "]") //
                                .name(property.getName()) //
                                .type(Type.SEMANTIC) //
                                .ext(Ext.builder() //
                                        .id("ext [" + property.getName() + "]") //
                                        .href("https://example.org/samples/ext/" + property.getName()) //
                                        .value("value goes here") //
                                        .build()) //
                                .rt("rt for [" + property.getName() + "]") //
                                .descriptor(Collections.singletonList(Descriptor.builder().id("embedded").build())) //
                                .build()) //
                        .collect(Collectors.toList()))
                .build();
    }

    @Autowired
    public GoalService setGoalService(GoalService goalService) {
        return this.goalService = goalService;
    }

    @Autowired
    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
}
