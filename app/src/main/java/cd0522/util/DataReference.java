package cd0522.util;

import cd0522.database.RentDatabase;
import cd0522.database.ToolDatabase;

/**
 * Stores references to constant data to be used across different application contexts.
 */
public class DataReference {
    public static final ToolDatabase TOOL_DATABASE = new ToolDatabase();
    public static final RentDatabase RENT_DATABASE = new RentDatabase();
}
