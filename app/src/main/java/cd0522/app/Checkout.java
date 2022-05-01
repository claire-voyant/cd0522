package cd0522.app;

import java.time.LocalDate;
import cd0522.data.RentalAgreement;
import cd0522.enums.ToolBrand;
import cd0522.enums.ToolCode;
import cd0522.enums.ToolType;
import cd0522.util.ApplicationConstants;
import cd0522.util.DiscountOutOfBoundsException;
import cd0522.util.RentalDayOutOfBoundsException;
import lombok.Builder;

@Builder
public class Checkout {
    private ToolCode toolCode;
    private int rentalDayCount;
    private int discountPercent;
    private LocalDate checkoutDate;

    public RentalAgreement generateRentalAgreement()
            throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException {
        // TODO finish building rental agreement
        if (discountPercent < ApplicationConstants.discountLowerBound) {
            throw new DiscountOutOfBoundsException(
                    "Discount percentage is below the minimum allowed amount of 0 percent.");
        }
        if (discountPercent > ApplicationConstants.discountUpperBound) {
            throw new DiscountOutOfBoundsException(
                    "Discount percentage is above the maximum allowed amount of 100 percent.");
        }
        if (rentalDayCount < ApplicationConstants.rentalDayMinimum) {
            throw new RentalDayOutOfBoundsException(
                    "Rental day count is below the minimum allowed amount of 1 day.");
        }
        return RentalAgreement.builder().toolCode(toolCode)
                .toolType(ToolType.Chainsaw)
                .toolBrand(ToolBrand.DeWalt)
                .rentalDays(rentalDayCount).checkoutDate(checkoutDate)
                .dueDate(LocalDate.now()).dailyRentalCharge(0.0)
                .chargeDays(0).prediscountCharge(0.0)
                .discountPercent(discountPercent).discountAmount(0.0)
                .finalCharge(0.0).build();
    }
}
