package com.zprogress.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Goal implements Serializable {

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

    @Override
    public String toString() {
        return "AbstractGoal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
