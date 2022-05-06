package cd0522.util;

/**
 * Thrown when the specified number of rental days in a rental agreement is out of the minimum
 * value.
 */
public class RentalDayOutOfBoundsException extends Exception {
    public RentalDayOutOfBoundsException(String message) {
        super(message);
    }
}
