package com.zprogress.reminder;

import java.time.LocalDateTime;

public class YearlyReminderCalculator implements ReminderCalculator {
    @Override
    public LocalDateTime calculateNextReminderDateTime() {
        return LocalDateTime.now().plusYears(1);
    }
}
