package cd0522.database;

import java.util.Map;
import java.util.Set;

import cd0522.data.RentalCharge;
import cd0522.enums.ToolType;

public final class RentDatabase extends Database<RentalCharge> {
    public RentDatabase(Map<Integer, RentalCharge> database) {
        super(database);
    }

    @Override
    public Set<RentalCharge> getInitialValues() {
        return Set.of(
                new RentalCharge(ToolType.Ladder, 1.99, true, true,
                        false),
                new RentalCharge(ToolType.Chainsaw, 1.49, true, false,
                        true),
                new RentalCharge(ToolType.Jackhammer, 2.99, true,
                        false, false));
    }
}
