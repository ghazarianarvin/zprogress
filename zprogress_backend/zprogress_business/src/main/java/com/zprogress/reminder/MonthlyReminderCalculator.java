package com.zprogress.reminder;

import java.time.LocalDateTime;

public class MonthlyReminderCalculator implements ReminderCalculator {
    @Override
    public LocalDateTime calculateNextReminderDateTime() {
        return LocalDateTime.now().plusMonths(1);
    }
}
