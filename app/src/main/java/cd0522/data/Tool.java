package cd0522.data;

import cd0522.enums.*;

/**
 * An entry for the tool database.
 * 
 * @param toolCode alpha code for a specific tool
 * @param toolType common word for the tool
 * @param brand    name of the brand
 */
public record Tool(ToolCode toolCode, ToolType toolType,
        ToolBrand brand) implements Indexable {
    public int getIndex() {
        return toolCode.getIndex();
    }
}
