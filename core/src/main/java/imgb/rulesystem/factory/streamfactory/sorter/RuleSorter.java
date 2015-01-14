package imgb.rulesystem.factory.streamfactory.sorter;

import org.apache.log4j.Logger;
import imgb.rulesystem.node.BaseNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ca on 7/15/14.<br>
 *     规则排序器，能够返回排序完成的节点
 */
public class RuleSorter {
    private static final Logger logger = Logger.getLogger(RuleSorter.class);

    private List<RuleUnit> ruleList = new ArrayList<>(); //储存排序对象
    private PriorityManager priorityManager;

    /**
     *
     * @param priorityManager 优先级排序器，能够获得当前对应basenode的优先级值
     */
    public RuleSorter(PriorityManager priorityManager) {
        this.priorityManager = priorityManager;
    }

    /**
     * 通过priorityManager 获得当前rootNode的优先级值，添加被排序的节点
     * @param rootNode 需要被排序插入的node值
     */
    public void addRule(BaseNode rootNode) {
        if(rootNode == null) {
            logger.warn("rule node is null, quit");
            return;
        }
        Float priority = priorityManager.getPriority(rootNode);
        ruleList.add(new RuleUnit(priority, rootNode));
    }

    /**
     * this function
     *
     * @return a list of sub-nodeinfo root nodes and the list is sorted by<br> 倒序输出
     * descending order of privilage.
     */
    public List<BaseNode> getSortedNodes() {

        ArrayList<BaseNode> returnArray = new ArrayList<BaseNode>();
        Object[] ruleUnits = ruleList.toArray();
        Arrays.sort(ruleUnits);

        for (Object unit : ruleUnits) {
            BaseNode tempNode = ((RuleUnit) unit).getNode();
            returnArray.add(tempNode);
        }

        return returnArray;
    }

    class RuleUnit implements Comparable<RuleUnit> {

        private float priority;
        private BaseNode node;

        public RuleUnit(float priority, BaseNode node) {
            this.priority = priority;
            this.node = node;
        }

        public float getPriority() {
            return priority;
        }

        public BaseNode getNode() {
            return node;
        }

        public int compareTo(RuleUnit unit) {
            return (int)(unit.getPriority() - priority);
        }
    }
}

