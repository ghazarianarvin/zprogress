package com.zprogress.reminder;

import com.zprogress.domain.Repetition;

public class ReminderCalculatorFactory {

    private static ReminderCalculatorFactory instance = new ReminderCalculatorFactory();

    private ReminderCalculatorFactory() {

    }

    public static ReminderCalculator reminderCalculatorFor(Repetition repetition) {
        switch (repetition) {
            case DAILY:
                return new DailyReminderCalculator();
            case WEEKLY:
                return new WeeklyReminderCalculator();
            case MONTHLY:
                return new MonthlyReminderCalculator();
            default: // yearly
                return new YearlyReminderCalculator();
        }
    }
}
