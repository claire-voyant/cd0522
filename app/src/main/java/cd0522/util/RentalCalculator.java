package cd0522.util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import cd0522.data.RentalCharge;

/**
 * A collection of functions and methods which encapsulate the business logic of rent calculation.
 */
public class RentalCalculator {
    /**
     * Calculate the number of days to charge the rent.
     * 
     * @param checkoutDate the local date the rent begins
     * @param rentalDays   the number of days in the rent agreement
     * @param rentalCharge the single day charge and charge day matrix
     * @return the number of chargable days per the rental agreement
     */
    public static int calculateChargeDays(LocalDate checkoutDate, int rentalDays,
            RentalCharge rentalCharge) {
        int chargeDays = 0;
        List<LocalDate> holidayList = CalendarValidator.holidaysInRange(checkoutDate, rentalDays);
        LocalDate startDay = checkoutDate.plusDays(1);
        LocalDate endCheckDate = checkoutDate.plusDays(rentalDays + 1);
        for (LocalDate dayInRange : startDay.datesUntil(endCheckDate)
                .collect(Collectors.toList())) {
            if (isChargeDay(dayInRange, rentalCharge, holidayList)) {
                chargeDays++;
            }
        }
        return chargeDays;
    }

    /**
     * Determine if a specific local date is a chargable day.
     * 
     * @param date         the local date to inspect
     * @param rentalCharge the single day charge and charge day matrix
     * @param holidayList  the list of holidays in the renting date range
     * @return true if the supplied local date is chargable false otherwise
     */
    public static boolean isChargeDay(LocalDate date, RentalCharge rentalCharge,
            List<LocalDate> holidayList) {
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
