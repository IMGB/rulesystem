package imgb.rulesystem.node.list;

import imgb.rulesystem.context.Context;
import imgb.rulesystem.node.information.executioninfo.NodeListReturnInfo;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.node.exception.NodeExecutionException;

import java.util.List;

/**
 * Created by is on 1/13/15.
 */
public class TopSequenceNode extends SequenceNode {
    public boolean execute(Context context) throws NodeExecutionException {
        boolean result = true;
        int count = 0;
        List childNodes = null;
        NodeListReturnInfo returnInfo = new NodeListReturnInfo();
        BaseNode child = null;
        childNodes = this.getChildren();
        count = this.getNodesCount();
        for (int i = 0; i < count; i++) {
            child = (BaseNode) (childNodes.get(i));
            result = child.execute(context);
            if (!result) {
                context.setRecord(Context.LAST_RULE_EXECUTED_NODE, child);
                break;
            }
        }
        return result;
    }
}
