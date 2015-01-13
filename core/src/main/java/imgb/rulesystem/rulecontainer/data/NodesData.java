package imgb.rulesystem.rulecontainer.data;


import imgb.rulesystem.node.BaseNode;

/**
 * Created by ca on 10/20/14.<br>
 */
public abstract class NodesData <T extends BaseNode> {
    public abstract boolean insert(Long ketvalue, T node);
    public abstract boolean clear();
    public abstract T search(Long keyValue);
}
