package cd0522.util;

import java.time.format.DateTimeFormatter;

/**
 * A set of constants to be used across the application.
 */
public class ApplicationConstants {
    public final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yy");
    public static final int discountLowerBound = 0;
    public static final int discountUpperBound = 100;
    public static final int rentalDayMinimum = 1;
}
