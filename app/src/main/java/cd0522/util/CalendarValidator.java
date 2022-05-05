package cd0522.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<LocalDate> holidaysInRange(LocalDate date,
            int dayRange) {
        List<LocalDate> holidayList = new ArrayList<>();
        LocalDate startDay = date.plusDays(1);
        LocalDate endCheckDate = date.plusDays(dayRange + 1);
        for (LocalDate dayInRange : startDay.datesUntil(endCheckDate)
                .collect(Collectors.toList())) {
            if (isLaborDay(dayInRange)) {
                holidayList.add(dayInRange);
            } else if (isIndependenceDay(dayInRange)) {
                holidayList.add(
                        whenIsIndependenceDayObserved(dayInRange));
            }
        }
        return holidayList;
    }

    public static LocalDate whenIsIndependenceDayObserved(
            LocalDate date) {
        if (isIndependenceDay(date) && isWeekendDay(date)) {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                return date.minusDays(1);
            } else {
                return date.plusDays(1);
            }
        } else {
            throw new IllegalArgumentException(String
                    .format("%s is not Independence day!", date));
        }
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
