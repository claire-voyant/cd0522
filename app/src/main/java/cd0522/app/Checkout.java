package cd0522.app;

import java.time.ZonedDateTime;

import cd0522.data.RentalAgreement;
import cd0522.enums.ToolCode;
import lombok.Builder;

@Builder
public class Checkout {
    private ToolCode toolCode;
    private int rentalDayCount;
    private double discountPercent;
    private ZonedDateTime checkoutDate;

    public RentalAgreement generateRentalAgreement() {
        // TODO finish building rental agreement
        return RentalAgreement.builder().toolCode(toolCode)
                .rentalDays(rentalDayCount)
                .discountPercent(discountPercent)
                .checkoutDate(checkoutDate).build();
    }
}
