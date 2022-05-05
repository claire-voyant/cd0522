package cd0522.enums;

import cd0522.data.Indexable;

/**
 * Common word for a tool.
 */
public enum ToolType implements Indexable {
    Chainsaw, Ladder, Jackhammer;

    @Override
    public int getIndex() {
        return this.ordinal();
    }
}
