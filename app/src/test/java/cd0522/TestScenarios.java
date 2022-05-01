package cd0522;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import cd0522.app.Checkout;
import cd0522.enums.ToolCode;
import cd0522.util.DiscountOutOfBoundsException;
import cd0522.util.RentalDayOutOfBoundsException;

import static cd0522.util.ApplicationConstants.dateFormat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;;

public class TestScenarios {
    public void testScenario(ToolCode toolCode, String dateTimeFormat,
            int rentalDayCount, int discountPercent)
            throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException {
        Checkout checkout = Checkout.builder().toolCode(toolCode)
                .checkoutDate(
                        LocalDate.parse(dateTimeFormat, dateFormat))
                .rentalDayCount(rentalDayCount)
                .discountPercent(discountPercent).build();
        System.out.println(checkout.generateRentalAgreement());
    }

    @Test
    public void firstTestScenario()
            throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException {
        assertThrows(DiscountOutOfBoundsException.class, () -> {
            testScenario(ToolCode.JAKR, "9/3/15", 5, 101);
        });
    }

    @Test
    public void secondTestScenario()
            throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException {
        assertDoesNotThrow(() -> {
            testScenario(ToolCode.LADW, "7/2/20", 3, 10);
        });
    }
}
