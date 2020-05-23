package com.zprogress.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

// TODO move validation annotations to dto
public class Step extends Resource {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull // for hypermedia
    @Min(1)
    @Max(5)
    private int importance;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private Repetition repetitionType;
    private DayOfWeek repetitionDay; // not null if repetitionType == weekly

    private LocalDateTime nextReminderDate; // if repetition type is weekly, monthly or years

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
