package cd0522.util;

/**
 * Thrown when the specified discount percentage in a rental agreement is out of bounds of the
 * minimum or maximum bounds.
 */
public class DiscountOutOfBoundsException extends Exception {
    public DiscountOutOfBoundsException(String message) {
        super(message);
    }
}
