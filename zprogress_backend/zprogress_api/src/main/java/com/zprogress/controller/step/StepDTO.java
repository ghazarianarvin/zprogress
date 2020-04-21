package com.zprogress.controller.step;

import com.zprogress.domain.Repetition;
import com.zprogress.domain.Step;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StepDTO {

    private Long id;
    private String name;
    private int importance;
    private LocalDate startDate;
    private Repetition repetitionType;
    private DayOfWeek repetitionDay;
    private LocalDateTime nextReminderDate;

    public StepDTO(Step step) {
        this.setId(step.getId());
        this.setName(step.getName());
        this.setImportance(step.getImportance());
        this.setStartDate(step.getStartDate());
        this.setRepetitionType(step.getRepetitionType());
        this.setRepetitionDay(step.getRepetitionDay());
        this.setNextReminderDate(step.getNextReminderDate());
    }

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

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Repetition getRepetitionType() {
        return repetitionType;
    }

    public void setRepetitionType(Repetition repetitionType) {
        this.repetitionType = repetitionType;
    }

    public DayOfWeek getRepetitionDay() {
        return repetitionDay;
    }

    public void setRepetitionDay(DayOfWeek repetitionDay) {
        this.repetitionDay = repetitionDay;
    }

    public LocalDateTime getNextReminderDate() {
        return nextReminderDate;
    }

    public void setNextReminderDate(LocalDateTime nextReminderDate) {
        this.nextReminderDate = nextReminderDate;
    }
}
