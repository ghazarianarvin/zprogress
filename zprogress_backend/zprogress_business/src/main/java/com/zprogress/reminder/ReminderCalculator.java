package com.zprogress.reminder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ReminderCalculator {

    LocalDateTime calculateNextReminderDateTime(LocalDate startDate);
}
