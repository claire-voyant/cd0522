package cd0522.enums;

import cd0522.data.Indexable;

/**
 * Alpha code for a specific tool.
 */
public enum ToolCode implements Indexable {
    CHNS, LADW, JAKD, JAKR;

    @Override
    public int getIndex() {
        return this.ordinal();
    }
}