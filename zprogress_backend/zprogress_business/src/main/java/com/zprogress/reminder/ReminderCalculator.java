package com.zprogress.reminder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ReminderCalculator {

    public static final int HOUR = 15;

    public static final int MINUTE = 00;

    LocalDateTime calculateNextReminderDateTime(LocalDate startDate);
}
