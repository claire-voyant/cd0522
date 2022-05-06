package cd0522.database;

import java.util.Set;

import cd0522.data.Tool;
import cd0522.enums.*;

/**
 * Stores the tool data using a simplified database.
 */
public final class ToolDatabase extends Database<ToolCode, Tool> {
    @Override
    public Set<Tool> getInitialValues() {
        return Set.of(new Tool(ToolCode.CHNS, ToolType.Chainsaw, ToolBrand.Stihl),
                new Tool(ToolCode.LADW, ToolType.Ladder, ToolBrand.Werner),
                new Tool(ToolCode.JAKD, ToolType.Jackhammer, ToolBrand.DeWalt),
                new Tool(ToolCode.JAKR, ToolType.Jackhammer, ToolBrand.Ridgid));
    }
}
