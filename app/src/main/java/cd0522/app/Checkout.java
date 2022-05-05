package cd0522.app;

import java.time.LocalDate;
import java.util.Optional;

import cd0522.data.RentalAgreement;
import cd0522.data.RentalCharge;
import cd0522.data.Tool;
import cd0522.database.ToolDatabase;
import cd0522.enums.ToolBrand;
import cd0522.enums.ToolCode;
import cd0522.enums.ToolType;
import cd0522.util.ApplicationConstants;
import cd0522.util.DataReference;
import cd0522.util.DatabaseEntryNotFoundException;
import cd0522.util.DiscountOutOfBoundsException;
import cd0522.util.RentalDayOutOfBoundsException;
import lombok.Builder;

@Builder
public class Checkout {
    private ToolCode toolCode;
    private int rentalDayCount;
    private int discountPercent;
    private LocalDate checkoutDate;

    /**
     * Generates the rental agreement given a toolcode, rental day
     * count, discount percent, and checkout date.
     * <p>
     * Validates various error conditions and displays a helpful
     * message.
     * </p>
     * 
     * @return a rental agreement with charges and days calculated
     * @throws DatabaseEntryNotFoundException when an invalid database
     *                                            key is passed in
     * @throws RentalDayOutOfBoundsException  when rental days is less
     *                                            than one
     * @throws DiscountOutOfBoundsException   when discount percent is
     *                                            not between 1 and
     *                                            100
     */
    public RentalAgreement generateRentalAgreement()
            throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException,
            DatabaseEntryNotFoundException {
        // TODO finish building rental agreement
        checkErrorCases();
        Tool tool = DataReference.TOOL_DATABASE
                .retrieveEntry(toolCode).get();
        RentalCharge rentCharge = DataReference.RENT_DATABASE
                .retrieveEntry(tool.toolType()).get();
        return RentalAgreement.builder().toolCode(toolCode)
                .toolType(tool.toolType()).toolBrand(tool.brand())
                .rentalDays(rentalDayCount).checkoutDate(checkoutDate)
                .dueDate(LocalDate.now())
                .dailyRentalCharge(rentCharge.dailyCharge())
                .chargeDays(0).prediscountCharge(0.0)
                .discountPercent(discountPercent).discountAmount(0.0)
                .finalCharge(0.0).build();
    }

    private void checkErrorCases()
            throws DiscountOutOfBoundsException,
            RentalDayOutOfBoundsException,
            DatabaseEntryNotFoundException {
        if (discountPercent < ApplicationConstants.discountLowerBound) {
            throw new DiscountOutOfBoundsException(
                    "Discount percentage is below the minimum allowed amount of 0 percent.\n"
                            + "Please enter a discount percentage greater than or equal to 0.");
        }
        if (discountPercent > ApplicationConstants.discountUpperBound) {
            throw new DiscountOutOfBoundsException(
                    "Discount percentage is above the maximum allowed amount of 100 percent.\n"
                            + "Please enter a discount percentage less than or equal to 100.");
        }
        if (rentalDayCount < ApplicationConstants.rentalDayMinimum) {
            throw new RentalDayOutOfBoundsException(
                    "Rental day count is below the minimum allowed amount of 1 day.\n"
                            + "Please enter a rental day count greater than or equal to 1.");
        }
        Optional<Tool> optionalTool = DataReference.TOOL_DATABASE
                .retrieveEntry(toolCode);
        Optional<RentalCharge> optionalRentCharge = DataReference.RENT_DATABASE
                .retrieveEntry(optionalTool.get().toolType());
        if (!optionalTool.isPresent()) {
            throw new DatabaseEntryNotFoundException(String.format(
                    "ToolDatabase does not have entry for %s.\n"
                            + "Please enter a valid tool code.",
                    toolCode));
        } else if (!optionalRentCharge.isPresent()) {
            throw new DatabaseEntryNotFoundException(String.format(
                    "RentDatabase does not have entry for %s.\n"
                            + "Please enter a valid tool type.",
                    optionalTool.get().toolType()));
        }
    }
}
