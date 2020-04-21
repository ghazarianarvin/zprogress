package com.zprogress.controller.goal;

import com.zprogress.controller.step.StepDTO;
import com.zprogress.domain.Goal;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Relation(collectionRelation = "goals")
public class GoalDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private List<StepDTO> steps;

    public GoalDTO(Goal goal) {
        this.setName(goal.getName());
        this.setDeadline(goal.getDeadline());
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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StepDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDTO> steps) {
        this.steps = steps;
    }
}
