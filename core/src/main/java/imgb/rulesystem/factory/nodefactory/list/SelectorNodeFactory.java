package imgb.rulesystem.factory.nodefactory.list;


import imgb.rulesystem.factory.nodefactory.list.ListNodeFactory;
import imgb.rulesystem.node.list.NodeList;
import imgb.rulesystem.factory.streamfactory.sorter.RuleSorter;
import imgb.rulesystem.node.list.SelectorNode;

/**
 * Created by Daniel on 2014/10/11.
 */
public class SelectorNodeFactory extends ListNodeFactory {
    @Override
    public NodeList createListNode(RuleSorter sorter) {
        return assembleNodeList(new SelectorNode(), sorter);
    }
}
