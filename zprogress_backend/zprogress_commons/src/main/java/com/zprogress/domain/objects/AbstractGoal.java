package com.zprogress.domain.objects;

import java.time.LocalDate;

public abstract class Goal {

    private String name;
    private String description;
    private LocalDate deadline;

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
        return "Goal{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
