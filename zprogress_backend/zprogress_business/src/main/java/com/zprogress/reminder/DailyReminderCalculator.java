package com.zprogress.reminder;

import java.time.LocalDateTime;

public class DailyReminderCalculator implements ReminderCalculator {
    @Override
    public LocalDateTime calculateNextReminderDateTime() {
        return LocalDateTime.now().plusDays(1);
    }
}
