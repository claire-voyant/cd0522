package cd0522.util;

import java.time.format.DateTimeFormatter;

/**
 * A set of constants to be used across the application.
 */
public class ApplicationConstants {
    public final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yy");
    public final static DateTimeFormatter dateFormatOutput = DateTimeFormatter
            .ofPattern("MM/dd/yy");
    public static final int discountLowerBound = 0;
    public static final int discountUpperBound = 100;
    public static final int rentalDayMinimum = 1;
    public static final double roundPrecision = 100.0;
    public static final double discountPrecision = 100.0;
}
