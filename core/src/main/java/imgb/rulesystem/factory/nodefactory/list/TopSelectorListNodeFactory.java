package imgb.rulesystem.factory.nodefactory.list;


import imgb.rulesystem.factory.nodefactory.list.ListNodeFactory;
import imgb.rulesystem.factory.streamfactory.sorter.RuleSorter;
import imgb.rulesystem.node.list.NodeList;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.node.list.TopSelectorNode;

/**
 * Created by ca on 10/13/14.<br>
 */
public class TopSelectorListNodeFactory extends ListNodeFactory {

    @Override
    protected NodeList assembleNodeList(NodeList nodeList, RuleSorter ruleSorter) {
        if(nodeList == null || ruleSorter == null) {
            return null;
        }

        for (BaseNode subRootNode : ruleSorter.getSortedNodes()) {
            nodeList.addSubNode(subRootNode);
        }
        return nodeList;
    }

    @Override
    public NodeList createListNode(RuleSorter sorter) {
        return assembleNodeList(new TopSelectorNode(), sorter);
    }
}
