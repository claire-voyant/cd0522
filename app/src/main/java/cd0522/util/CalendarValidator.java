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
 * A collection of functions and methods which encapsulate the business logic of calendar
 * verification.
 */
public class CalendarValidator {
    /**
     * Determine if a local date is a week day.
     * 
     * @param date the local date to inspect
     * @return true if the day is Monday-Friday false otherwise
     */
    public static boolean isWeekDay(LocalDate date) {
        return !isWeekendDay(date);
    }

    /**
     * Determine if a local date is a weekend day.
     * 
     * @param date the local date to inspect
     * @return true if the day is Saturday or Sunday false otherwise
     */
    public static boolean isWeekendDay(LocalDate date) {
        DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    /**
     * Determine if a local day is a holiday.
     * 
     * @param date the local date to inspect
     * @return true if the day is independence of labor day false otherwise
     */
    public static boolean isHoliday(LocalDate date) {
        return isIndependenceDay(date) || isLaborDay(date);
    }

    /**
     * Determine which days are holiday within the date range.
     * 
     * @param date     beginning of the local date range
     * @param dayRange the number of days spanning the range
     * @return the list of local dates which are an observed holiday
     */
    public static List<LocalDate> holidaysInRange(LocalDate date, int dayRange) {
        List<LocalDate> holidayList = new ArrayList<>();
        LocalDate startDay = date.plusDays(1);
        LocalDate endCheckDate = date.plusDays(dayRange + 1);
        for (LocalDate dayInRange : startDay.datesUntil(endCheckDate)
                .collect(Collectors.toList())) {
            if (isLaborDay(dayInRange)) {
                holidayList.add(dayInRange);
            } else if (isIndependenceDay(dayInRange)) {
                holidayList.add(whenIsIndependenceDayObserved(dayInRange));
            }
        }
        return holidayList;
    }

    /**
     * Determine when an independence day is observed.
     * <p>
     * A precondition for this function is for the supplied local date to be independence day.
     * Throws an exception if the supplied date is not independence day.
     * </p>
     * 
     * @param date the local date to inspect
     * @return the day of observerence of independence day
     * @throws IllegalArgumentException if the supplied date is not independence day
     */
    public static LocalDate whenIsIndependenceDayObserved(LocalDate date) {
        if (isIndependenceDay(date)) {
            if (isWeekendDay(date)) {
                if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    return date.minusDays(1);
                } else {
                    return date.plusDays(1);
                }
            } else if (isWeekDay(date)) {
                return date;
            }
        }
        throw new IllegalArgumentException(String.format("%s is not Independence day!", date));
    }

    /**
     * Determine if the supplied local date is independence day.
     * 
     * @param date the local date to inspect
     * @return true if the month-day is July 4th false otherwise
     */
    public static boolean isIndependenceDay(LocalDate date) {
        MonthDay monthDay = MonthDay.of(Month.JULY, 4);
        return date.getMonth() == monthDay.getMonth()
                && date.getDayOfMonth() == monthDay.getDayOfMonth();
    }

    /**
     * Determine if the supplied local date is labor day.
     * 
     * @param date the local date to inspect
     * @return true if the date is the first Monday in September false otherwise
     */
    public static boolean isLaborDay(LocalDate date) {
        return date.getMonth() == Month.SEPTEMBER
                && date.equals(date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
    }
}
