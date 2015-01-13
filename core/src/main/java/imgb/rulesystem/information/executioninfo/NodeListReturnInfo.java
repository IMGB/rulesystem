package imgb.rulesystem.information.executioninfo;


import imgb.rulesystem.node.BaseNode;

/**
 * Created by Daniel on 2014/7/26.
 */
public class NodeListReturnInfo extends ExecutionInfo {
    private final static String RETURN_NODE = "RETURN_NODE";
    private final static String RETURN_INDEX = "RETURN_INDEX";

    public NodeListReturnInfo() {

    }

    public BaseNode getReturnedNode() {
        BaseNode returnNode = (BaseNode) (super.getInfo(RETURN_NODE));
        return returnNode;
    }

    public Integer getRetrunedIndex() {
        Integer retIndex = (Integer) (super.getInfo(RETURN_INDEX));
        return retIndex;
    }

    public void setInfo(BaseNode node, int index) {
        super.setInfo(RETURN_NODE, node);
        super.setInfo(RETURN_INDEX, index);
    }

}
