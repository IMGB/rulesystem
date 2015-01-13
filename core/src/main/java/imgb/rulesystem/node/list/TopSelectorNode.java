package imgb.rulesystem.node.list;


import imgb.rulesystem.context.Context;
import imgb.rulesystem.node.exception.NodeExecutionException;
import imgb.rulesystem.information.executioninfo.NodeListReturnInfo;
import imgb.rulesystem.node.BaseNode;

import java.util.List;

/**
 * Created by Daniel on 2014/7/26.
 */
public class TopSelectorNode extends SelectorNode {

    public TopSelectorNode() {

    }

    /**
     * 继承selector node 将当前selector返回的node值，保存在context中，使用Context.LAST_RULE_EXECUTED_NODE 作为key值
     * @param context
     * @return
     * @throws imgb.rulesystem.node.exception.NodeExecutionException
     */
    public boolean execute(Context context) throws NodeExecutionException {
        boolean result = false;
        int count = 0;
        List childNodes = null;
        NodeListReturnInfo returnInfo = new NodeListReturnInfo();

        BaseNode child = null;
        childNodes = this.getChildren();
        count = this.getNodesCount();
        for (int i = 0; i < count; i++) {
            child = (BaseNode) (childNodes.get(i));
            result = child.execute(context);
            if (result) {
                context.setRecord(Context.LAST_RULE_EXECUTED_NODE, child);
                break;
            }
        }
        return result;
    }

}
