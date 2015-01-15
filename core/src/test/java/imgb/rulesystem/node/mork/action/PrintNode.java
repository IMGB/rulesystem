package imgb.rulesystem.node.mork.action;

import com.fasterxml.jackson.databind.JsonNode;
import imgb.rulesystem.context.Context;
import imgb.rulesystem.node.action.ActionNode;
import imgb.rulesystem.node.exception.NodeExecutionException;

/**
 * Created by is on 1/15/15.
 */
public class PrintNode extends ActionNode {

    private String printStr;

    public PrintNode(String printStr) {
        this.printStr = printStr;
    }

    @Override
    public boolean execute(Context context) throws NodeExecutionException {
        context.setRecord("node:" + this.hashCode() + " print", printStr);
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
