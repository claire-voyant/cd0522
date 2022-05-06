package cd0522;

import java.time.LocalDate;

import cd0522.app.Checkout;
import cd0522.enums.ToolCode;
import cd0522.util.DatabaseEntryNotFoundException;
import cd0522.util.DiscountOutOfBoundsException;
import cd0522.util.RentalDayOutOfBoundsException;

import static cd0522.util.ApplicationConstants.dateFormat;

/**
 * Main file for testing purposes and demonstration.
 */
public class Main {
    public static void main(String args[]) throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException, DatabaseEntryNotFoundException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not enough arguments given! Requires 4: "
                    + "toolCode checkoutDate rentalDayCount discountPercent");
        }
        ToolCode toolCode = determineToolCode(args[0]);
        LocalDate date = LocalDate.parse(args[1], dateFormat);
        int rentalDayCount = Integer.parseInt(args[2]);
        int discountPercent = Integer.parseInt(args[3]);
        Checkout checkout = Checkout.builder().toolCode(toolCode).checkoutDate(date)
                .rentalDayCount(rentalDayCount).discountPercent(discountPercent).build();
        System.out.println(checkout.generateRentalAgreement());
    }

    private static ToolCode determineToolCode(String toolCodeString) {
        if (toolCodeString.equals("CHNS")) {
            return ToolCode.CHNS;
        } else if (toolCodeString.equals("LADW")) {
            return ToolCode.LADW;
        } else if (toolCodeString.equals("JAKD")) {
            return ToolCode.JAKD;
        } else if (toolCodeString.equals("JAKR")) {
            return ToolCode.JAKR;
        } else {
            throw new IllegalArgumentException(
                    String.format("Unsupported tool code %s!", toolCodeString));
        }
    }
}
