package cd0522.app;

import java.time.LocalDate;
import cd0522.data.RentalAgreement;
import cd0522.enums.ToolBrand;
import cd0522.enums.ToolCode;
import cd0522.enums.ToolType;
import lombok.Builder;

@Builder
public class Checkout {
    private ToolCode toolCode;
    private int rentalDayCount;
    private int discountPercent;
    private LocalDate checkoutDate;

    public RentalAgreement generateRentalAgreement() {
        // TODO finish building rental agreement
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
