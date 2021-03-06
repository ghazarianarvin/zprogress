package com.zprogress.controller.goal;

import com.zprogress.domain.DomainTransformer;
import com.zprogress.domain.Goal;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Relation(collectionRelation = "goals")
public class GoalDTO implements DomainTransformer<Goal>, Serializable {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private LocalDate df_deadline;

    public GoalDTO() {}

    public GoalDTO(Goal goal) {
        this.setName(goal.getName());
        this.setDf_deadline(goal.getDeadline());
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

    public LocalDate getDf_deadline() {
        return df_deadline;
    }

    public void setDf_deadline(LocalDate df_deadline) {
        this.df_deadline = df_deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Goal toDomainType() {
        var goal = new Goal();
        goal.setDeadline(this.df_deadline);
        goal.setDescription(this.description);
        goal.setName(this.name);
        return goal;
    }
}
