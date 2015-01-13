package imgb.rulesystem.factory.nodefactory.list;


import imgb.rulesystem.factory.streamfactory.sorter.RuleSorter;
import imgb.rulesystem.node.list.NodeList;
import imgb.rulesystem.node.BaseNode;


/**
 * Created by Daniel on 2014/10/11.
 * node list 工厂，通过ruleSorter 构造排序的node list
 */
public abstract class ListNodeFactory {

    /**
     *
     * @param ruleSorter 通过当前的规则 rulesorter 获得排序完的节点
     * @return
     */
    public abstract NodeList createListNode(RuleSorter ruleSorter);


    /**
     * 通过ruleSorter 使用默认的顺序组装，并返回一个被插入数个节点的nodeList
     * @param nodeList 需要被插入节点的 list
     * @param ruleSorter 规则排序器，能返回排序完成的nodes
     * @return
     */
    protected NodeList assembleNodeList(NodeList nodeList, RuleSorter ruleSorter) {
        if(nodeList == null || ruleSorter == null) {
            return null;
        }

        for (BaseNode subRootNode : ruleSorter.getSortedNodes()) {
            nodeList.addSubNode(subRootNode);
        }

        if(nodeList.getNodesCount() == 0) {
            return null;
        }
        return nodeList;
    }
}
