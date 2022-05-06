package cd0522.database;

import java.util.Set;

import cd0522.data.RentalCharge;
import cd0522.enums.ToolType;

/**
 * Stores the rental data using a simplified database.
 */
public final class RentDatabase extends Database<ToolType, RentalCharge> {
    @Override
    public Set<RentalCharge> getInitialValues() {
        return Set.of(new RentalCharge(ToolType.Ladder, 1.99, true, true, false),
                new RentalCharge(ToolType.Chainsaw, 1.49, true, false, true),
                new RentalCharge(ToolType.Jackhammer, 2.99, true, false, false));
    }
}
