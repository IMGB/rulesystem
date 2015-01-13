package imgb.rulesystem.information.executioninfo;


import imgb.rulesystem.node.BaseNode;

/**
 * Created by Daniel on 2014/7/26.
 */
public class NodeListExecutionInfo extends ExecutionInfo {
    private final static String EXECUTEION_NODE = "exectionNode";
    private final static String EXECUTETION_INDEX = "exectionIndex";
    private boolean started;

    public NodeListExecutionInfo(BaseNode node, int index, boolean started) {
        super.setInfo(EXECUTEION_NODE, node);
        super.setInfo(EXECUTETION_INDEX, index);
        this.started = started;
    }

    public BaseNode getExectionNode() {
        BaseNode returnNode = (BaseNode) (super.getInfo(EXECUTEION_NODE));
        return returnNode;
    }

    public Integer getExectionIndex() {
        Integer retIndex = (Integer) (super.getInfo(EXECUTETION_INDEX));
        return retIndex;
    }

    private boolean getStartedStatus() {
        return started;
    }
}
