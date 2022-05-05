package cd0522.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

/**
 * A collection of functions and methods which encapsulate the
 * business logic of calendar verification.
 */
public class CalendarValidator {
    public static boolean isWeekDay(LocalDate date) {
        return !isWeekendDay(date);
    }

    public static boolean isWeekendDay(LocalDate date) {
        DayOfWeek dayOfWeek = DayOfWeek
                .of(date.get(ChronoField.DAY_OF_WEEK));
        return dayOfWeek == DayOfWeek.SUNDAY
                || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public static boolean isHoliday(LocalDate date) {
        return isIndependenceDay(date) || isLaborDay(date);
    }

    public static boolean isIndependenceDay(LocalDate date) {
        MonthDay monthDay = MonthDay.of(Month.JULY, 4);
        return date.getMonth() == monthDay.getMonth()
                && date.getDayOfMonth() == monthDay.getDayOfMonth();
    }

    public static boolean isLaborDay(LocalDate date) {
        return date.getMonth() == Month.SEPTEMBER
                && date.equals(date.with(TemporalAdjusters
                        .firstInMonth(DayOfWeek.MONDAY)));
    }
}
