package imgb.rulesystem.rulecontainer.data;


import imgb.rulesystem.node.BaseNode;

/**
 * Created by ca on 10/20/14.<br>
 */
public class NodeContainer<T extends BaseNode> {

    private T ruleNode;
    private Long keyValue;

    public NodeContainer(T ruleNode, Long keyValue) {
        this.ruleNode = ruleNode;
        this.keyValue = keyValue;
    }

    public Long getKeyValue() {
        return keyValue;
    }

    public T getNode() {
        return ruleNode;
    }
}
