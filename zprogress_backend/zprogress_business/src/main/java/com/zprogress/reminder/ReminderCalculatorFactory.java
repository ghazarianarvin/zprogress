package com.zprogress.reminder;

import com.zprogress.domain.Repetition;

public class ReminderCalculatorFactory {

    private static ReminderCalculatorFactory instance = new ReminderCalculatorFactory();

    private ReminderCalculatorFactory() {

    }

    public static ReminderCalculator reminderCalculatorFor(Repetition repetition) {
        switch (repetition) {
            case daily:
                return new DailyReminderCalculator();
            case weekly:
                return new WeeklyReminderCalculator();
            case monthly:
                return new MonthlyReminderCalculator();
            default: // yearly
                return new YearlyReminderCalculator();
        }
    }
}
