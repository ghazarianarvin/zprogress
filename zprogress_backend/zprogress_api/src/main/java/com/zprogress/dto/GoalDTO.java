package com.zprogress.dto;

import com.zprogress.domain.Goal;
import org.springframework.hateoas.EntityModel;

import java.time.LocalDate;

public class GoalDTO extends EntityModel<Goal> {

    public GoalDTO() {

    }

    public GoalDTO(Goal goal) {
        super(goal);
//        add(linkTo(methodOn(GoalController.class).get(goal.getId())).withSelfRel());
    }

    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
