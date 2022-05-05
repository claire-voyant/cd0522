package cd0522.util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import cd0522.data.RentalCharge;

/**
 * A collection of functions and methods which encapsulate the
 * business logic of rent calculation.
 */
public class RentalCalculator {
    public static double calculateTotalCharge(LocalDate checkoutDate,
            int rentalDays, RentalCharge rentalCharge) {
        return 0.0; // TODO
    }

    public static int calculateChargeDays(LocalDate checkoutDate,
            int rentalDays, RentalCharge rentalCharge) {
        int chargeDays = 0;
        List<LocalDate> holidayList = CalendarValidator
                .holidaysInRange(checkoutDate, rentalDays);
        LocalDate startDay = checkoutDate.plusDays(1);
        LocalDate endCheckDate = checkoutDate
                .plusDays(rentalDays + 1);
        for (LocalDate dayInRange : startDay.datesUntil(endCheckDate)
                .collect(Collectors.toList())) {
            if (isChargeDay(dayInRange, rentalCharge, holidayList)) {
                chargeDays++;
            }
        }
        return chargeDays;
    }

    public static boolean isChargeDay(LocalDate date,
            RentalCharge rentalCharge, List<LocalDate> holidayList) {
        if (holidayList.contains(date)) {
            return rentalCharge.holidayCharge();
        } else if (CalendarValidator.isWeekDay(date)) {
            return rentalCharge.weekDayCharge();
        } else if (CalendarValidator.isWeekendDay(date)) {
            return rentalCharge.weekendCharge();
        } else {
            return true;
        }
    }
}
