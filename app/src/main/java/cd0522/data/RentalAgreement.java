package cd0522.data;

import java.time.ZonedDateTime;

import cd0522.enums.*;
import lombok.Builder;
import lombok.Data;

/**
 * The rental agreement to be generated by the checkout.
 */
@Data
@Builder
public class RentalAgreement {
    private ToolCode toolCode;
    private ToolType toolType;
    private ToolBrand toolBrand;
    private int rentalDays;
    private ZonedDateTime checkoutDate;
    private ZonedDateTime dueDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountPercent;
    private double discountAmount;
    private double finalCharge;
}
