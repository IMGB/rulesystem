package imgb.rulesystem.factory.streamfactory;


import imgb.rulesystem.factory.nodefactory.leaf.LeafNodeFactory;
import imgb.rulesystem.factory.streamfactory.sorter.RuleSorter;
import imgb.rulesystem.node.list.NodeList;
import imgb.rulesystem.rulereader.TokenException;
import imgb.rulesystem.rulereader.reader.RuleReader;
import org.apache.log4j.Logger;
import imgb.rulesystem.exception.FactoryException;
import imgb.rulesystem.factory.nodefactory.list.ListNodeFactory;
import imgb.rulesystem.factory.streamfactory.sorter.PriorityManager;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.rulereader.token.RuleToken;

/**
 * Created by Daniel on 2014/10/11.
 * 通过priorityManager 创建经过排序的ListNode的stream factory
 */
public class SortStreamFactory extends StreamFactory {
    private static final Logger logger = Logger.getLogger(SortStreamFactory.class);

    private PriorityManager priorityManager;
    private MapFactory mapFactory;
    private ListNodeFactory listNodeFactory;

    /**
     * 构造函数
     *
     * @param priorityManager 优先级管理器
     * @param mapFactory      node的map工厂
     * @param listNodeFactory 当前生成的NodeList的工厂
     */
    public SortStreamFactory(PriorityManager priorityManager, MapFactory mapFactory, ListNodeFactory listNodeFactory) {
        this.priorityManager = priorityManager;
        this.mapFactory = mapFactory;
        this.listNodeFactory = listNodeFactory;
    }

    public SortStreamFactory(ListNodeFactory listNodeFactory) {
        this.listNodeFactory = listNodeFactory;
        this.priorityManager = new PriorityManager();
        this.mapFactory = new MapFactory();
    }

    public SortStreamFactory putStreamFactory(String tokenName, StreamFactory streamFactory, float priority) {
        mapFactory.putStreamFactory(tokenName, streamFactory);
        priorityManager.putPriority(tokenName, priority);
        return this;
    }

    public SortStreamFactory putLeafFactory(String tokenName, LeafNodeFactory nodeFactory, float priority) {
        mapFactory.putNodeFactory(tokenName, nodeFactory);
        priorityManager.putPriority(tokenName, priority);
        return this;
    }

    public SortStreamFactory putStreamFactory(String tokenName, StreamFactory streamFactory) {
        mapFactory.putStreamFactory(tokenName, streamFactory);
        priorityManager.putPriority(tokenName, PriorityManager.DEFAULT_PRIORITY);
        return this;
    }

    public SortStreamFactory putLeafFactory(String tokenName, LeafNodeFactory nodeFactory) {
        mapFactory.putNodeFactory(tokenName, nodeFactory);
        priorityManager.putPriority(tokenName, PriorityManager.DEFAULT_PRIORITY);
        return this;
    }

    /**
     * 通过mapFactory 获得对应持久化工厂的子节点。用以添加
     * 如果当前的token有一个PRIORITY_KEY， 标识为当前节点的优先级信息 存储到返回list节点的PRIORITY_KEY信息中
     *
     * @param reader 通过reader 获得流式tokens值 然后构造出当前的节点
     * @return
     * @throws FactoryException
     */
    @Override
    public BaseNode createNode(RuleReader reader) throws FactoryException {
        Float priorty = null;
        RuleSorter ruleSorter = new RuleSorter(priorityManager);

        try {
            while (reader.hasNext()) {

                RuleToken nowToken = reader.getNextToken();
                if (nowToken == null) {
                    throw new FactoryException("reader has next, bug get token is null.");
                }

                String tokenName = nowToken.getTokenName();

                if (PRIORITY_KEY.equals(tokenName)) {
                    priorty = Float.valueOf(nowToken.getTokenString());
                }

                assembleRuleSorter(reader, nowToken, ruleSorter);

                //判断当前的session 是否结束，并返回
                if (nowToken.beSessionEnd()) {
                    break;
                }
            }
            return getNodelist(ruleSorter,priorty);
        } catch (TokenException e) {
            throw new FactoryException(e);
        }
    }

    private void assembleRuleSorter(RuleReader reader, RuleToken nowToken,
                                    RuleSorter ruleSorter) throws FactoryException {
        BaseNode node = mapFactory.createNode(nowToken, reader);
        if (node == null) {
            logger.warn("in class " + this.getClass() + " can not get token:" + nowToken.getTokenName() + " session:" +
                    nowToken.beSessionEnd() + " corresponding rule node");
        } else {
            node.setNodeInfo(NODE_NAME, nowToken.getTokenName());
            ruleSorter.addRule(node);
        }
    }

    private NodeList getNodelist(RuleSorter ruleSorter, Float priorty) {
        NodeList nodeList = listNodeFactory.createListNode(ruleSorter);
        if (nodeList == null) {
            logger.warn("nodeList return null,ignore");
        }

        if (nodeList != null) {
            if (priorty != null) {
                nodeList.setNodeInfo(PRIORITY_KEY, priorty);
            }
        }
        return nodeList;
    }
}
