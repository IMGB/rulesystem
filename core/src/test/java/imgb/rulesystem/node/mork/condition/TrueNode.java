package imgb.rulesystem.node.mork.condition;

import com.fasterxml.jackson.databind.JsonNode;
import imgb.rulesystem.context.Context;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.node.exception.NodeExecutionException;

/**
 * Created by is on 1/15/15.
 */
public class TrueNode extends BaseNode{
    public TrueNode() {
    }

    @Override
    public boolean execute(Context context) throws NodeExecutionException {
        return true;
    }

    @Override
    public JsonNode serlizationToJson() {
        return null;
    }

    @Override
    public Object deserializeFromJson(JsonNode jsonNode) throws Exception {
        return null;
    }
}
