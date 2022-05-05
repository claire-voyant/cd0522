package cd0522.data;

import cd0522.enums.ToolType;

/**
 * The entry of the charge in the database.
 * 
 * @param dailyCharge   amount charge per day
 * @param weekDayCharge true if weekdays are chargable false otherwise
 * @param weekendCharge true if weekends are chargable false otherwise
 * @param holidayCharge true if holidays are chargaable false
 *                          otherwise
 */
public record RentalCharge(ToolType toolType, double dailyCharge,
        boolean weekDayCharge, boolean weekendCharge,
        boolean holidayCharge) implements Indexable {
    public int getIndex() {
        return toolType.getIndex();
    }
}
