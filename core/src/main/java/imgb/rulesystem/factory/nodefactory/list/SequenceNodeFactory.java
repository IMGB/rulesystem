package imgb.rulesystem.factory.nodefactory.list;


import imgb.rulesystem.factory.nodefactory.list.ListNodeFactory;
import imgb.rulesystem.factory.streamfactory.sorter.RuleSorter;
import imgb.rulesystem.node.list.NodeList;
import imgb.rulesystem.node.list.SequenceNode;

/**
 * Created by Daniel on 2014/10/11.
 */
public class SequenceNodeFactory extends ListNodeFactory {
    @Override
    public NodeList createListNode(RuleSorter sorter) {
        return assembleNodeList(new SequenceNode(), sorter);
    }
}
