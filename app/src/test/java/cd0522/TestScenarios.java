package cd0522;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import cd0522.app.Checkout;
import cd0522.enums.ToolCode;
import static cd0522.util.RentalCalculator.dateFormat;;

public class TestScenarios {
    public void testScenario(ToolCode toolCode, String dateTimeFormat,
            int rentalDayCount, int discountPercent) {
        Checkout checkout = Checkout.builder().toolCode(toolCode)
                .checkoutDate(
                        LocalDate.parse(dateTimeFormat, dateFormat))
                .rentalDayCount(rentalDayCount)
                .discountPercent(discountPercent).build();
        System.out.println(checkout.generateRentalAgreement());
    }

    @Test
    public void firstTestScenario() {
        testScenario(ToolCode.JAKR, "09/03/15", 5, 101);
    }
}
