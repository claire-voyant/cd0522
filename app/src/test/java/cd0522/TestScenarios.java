package cd0522;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import cd0522.app.Checkout;
import cd0522.data.RentalAgreement;
import cd0522.enums.ToolBrand;
import cd0522.enums.ToolCode;
import cd0522.enums.ToolType;
import cd0522.util.DatabaseEntryNotFoundException;
import cd0522.util.DiscountOutOfBoundsException;
import cd0522.util.RentalDayOutOfBoundsException;

import static cd0522.util.ApplicationConstants.dateFormat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;;

public class TestScenarios {
    public RentalAgreement testScenario(ToolCode toolCode,
            String date, int rentalDayCount, int discountPercent)
            throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException,
            DatabaseEntryNotFoundException {
        Checkout checkout = Checkout.builder().toolCode(toolCode)
                .checkoutDate(LocalDate.parse(date, dateFormat))
                .rentalDayCount(rentalDayCount)
                .discountPercent(discountPercent).build();
        RentalAgreement rentalAgreement = checkout
                .generateRentalAgreement();
        System.out.println(rentalAgreement);
        return rentalAgreement;
    }

    @Test
    public void firstTestScenario() {
        assertThrows(DiscountOutOfBoundsException.class, () -> {
            testScenario(ToolCode.JAKR, "9/3/15", 5, 101);
        });
    }

    @Test
    public void secondTestScenario()
            throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException,
            DatabaseEntryNotFoundException {
        RentalAgreement rentalAgreement = testScenario(ToolCode.LADW,
                "7/2/20", 3, 10);
        assertEquals(ToolCode.LADW, rentalAgreement.getToolCode());
        assertEquals(ToolType.Ladder, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Werner,
                rentalAgreement.getToolBrand());
        assertEquals(3, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2),
                rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 5),
                rentalAgreement.getDueDate());
        assertEquals(1.99, rentalAgreement.getDailyRentalCharge());
        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals(3.98, rentalAgreement.getPrediscountCharge());
        assertEquals(10, rentalAgreement.getDiscountPercent());
        assertEquals(0.40, rentalAgreement.getDiscountAmount());
        assertEquals(3.58, rentalAgreement.getFinalCharge());
    }
}
