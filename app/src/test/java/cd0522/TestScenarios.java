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

/**
 * Collection of test scenarios to prove functionality of the application.
 */
public class TestScenarios {
    public RentalAgreement testScenario(ToolCode toolCode, String date, int rentalDayCount,
            int discountPercent) throws DiscountOutOfBoundsException, RentalDayOutOfBoundsException,
            DatabaseEntryNotFoundException {
        Checkout checkout = Checkout.builder().toolCode(toolCode)
                .checkoutDate(LocalDate.parse(date, dateFormat)).rentalDayCount(rentalDayCount)
                .discountPercent(discountPercent).build();
        RentalAgreement rentalAgreement = checkout.generateRentalAgreement();
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
    public void secondTestScenario() throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException, DatabaseEntryNotFoundException {
        RentalAgreement rentalAgreement = testScenario(ToolCode.LADW, "7/2/20", 3, 10);
        assertEquals(ToolCode.LADW, rentalAgreement.getToolCode());
        assertEquals(ToolType.Ladder, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Werner, rentalAgreement.getToolBrand());
        assertEquals(3, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 5), rentalAgreement.getDueDate());
        assertEquals(1.99, rentalAgreement.getDailyRentalCharge());
        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals(3.98, rentalAgreement.getPrediscountCharge());
        assertEquals(10, rentalAgreement.getDiscountPercent());
        assertEquals(0.40, rentalAgreement.getDiscountAmount());
        assertEquals(3.58, rentalAgreement.getFinalCharge());
    }

    @Test
    public void thirdTestScenario() throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException, DatabaseEntryNotFoundException {
        RentalAgreement rentalAgreement = testScenario(ToolCode.CHNS, "7/2/15", 5, 25);
        assertEquals(ToolCode.CHNS, rentalAgreement.getToolCode());
        assertEquals(ToolType.Chainsaw, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Stihl, rentalAgreement.getToolBrand());
        assertEquals(5, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 7), rentalAgreement.getDueDate());
        assertEquals(1.49, rentalAgreement.getDailyRentalCharge());
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals(4.47, rentalAgreement.getPrediscountCharge());
        assertEquals(25, rentalAgreement.getDiscountPercent());
        assertEquals(1.12, rentalAgreement.getDiscountAmount());
        assertEquals(3.35, rentalAgreement.getFinalCharge());
    }

    @Test
    public void fourthTestScenario() throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException, DatabaseEntryNotFoundException {
        RentalAgreement rentalAgreement = testScenario(ToolCode.JAKD, "9/3/15", 6, 0);
        assertEquals(ToolCode.JAKD, rentalAgreement.getToolCode());
        assertEquals(ToolType.Jackhammer, rentalAgreement.getToolType());
        assertEquals(ToolBrand.DeWalt, rentalAgreement.getToolBrand());
        assertEquals(6, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 9, 3), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 9, 9), rentalAgreement.getDueDate());
        assertEquals(2.99, rentalAgreement.getDailyRentalCharge());
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals(8.97, rentalAgreement.getPrediscountCharge());
        assertEquals(0, rentalAgreement.getDiscountPercent());
        assertEquals(0.00, rentalAgreement.getDiscountAmount());
        assertEquals(8.97, rentalAgreement.getFinalCharge());
    }

    @Test
    public void fifthTestScenario() throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException, DatabaseEntryNotFoundException {
        RentalAgreement rentalAgreement = testScenario(ToolCode.JAKR, "7/2/15", 9, 0);
        assertEquals(ToolCode.JAKR, rentalAgreement.getToolCode());
        assertEquals(ToolType.Jackhammer, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Ridgid, rentalAgreement.getToolBrand());
        assertEquals(9, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 11), rentalAgreement.getDueDate());
        assertEquals(2.99, rentalAgreement.getDailyRentalCharge());
        assertEquals(5, rentalAgreement.getChargeDays());
        assertEquals(14.95, rentalAgreement.getPrediscountCharge());
        assertEquals(0, rentalAgreement.getDiscountPercent());
        assertEquals(0.00, rentalAgreement.getDiscountAmount());
        assertEquals(14.95, rentalAgreement.getFinalCharge());
    }

    @Test
    public void sixthTestScenario() throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException, DatabaseEntryNotFoundException {
        RentalAgreement rentalAgreement = testScenario(ToolCode.JAKR, "7/2/20", 4, 50);
        assertEquals(ToolCode.JAKR, rentalAgreement.getToolCode());
        assertEquals(ToolType.Jackhammer, rentalAgreement.getToolType());
        assertEquals(ToolBrand.Ridgid, rentalAgreement.getToolBrand());
        assertEquals(4, rentalAgreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), rentalAgreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 6), rentalAgreement.getDueDate());
        assertEquals(2.99, rentalAgreement.getDailyRentalCharge());
        assertEquals(1, rentalAgreement.getChargeDays());
        assertEquals(2.99, rentalAgreement.getPrediscountCharge());
        assertEquals(50, rentalAgreement.getDiscountPercent());
        assertEquals(1.5, rentalAgreement.getDiscountAmount());
        assertEquals(1.49, rentalAgreement.getFinalCharge());
    }
}
