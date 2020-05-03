package com.zprogress.controller.goal;

import com.zprogress.domain.Goal;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Relation(collectionRelation = "goals")
public class GoalDTO implements Serializable {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private LocalDate d_deadline;

    public GoalDTO(Goal goal) {
        this.setName(goal.getName());
        this.setD_deadline(goal.getDeadline());
        this.setDescription(goal.getDescription());
        this.setId(goal.getId());
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

    public LocalDate getD_deadline() {
        return d_deadline;
    }

    public void setD_deadline(LocalDate d_deadline) {
        this.d_deadline = d_deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
