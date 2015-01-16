package imgb.rulesystem.factory.nodefactory.list;

import imgb.rulesystem.factory.streamfactory.StreamFactory;
import imgb.rulesystem.factory.streamfactory.sorter.PriorityManager;
import imgb.rulesystem.factory.streamfactory.sorter.RuleSorter;
import imgb.rulesystem.node.list.NodeList;
import imgb.rulesystem.node.list.SelectorNode;
import junit.framework.TestCase;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public class TopSelectorListNodeFactoryTest extends TestCase {
    Map<String, Float> priorityMap = new HashMap<>();
    PriorityManager priorityManager = new PriorityManager(priorityMap);
    RuleSorter ruleSorter = new RuleSorter(priorityManager);
    TopSelectorListNodeFactory topSelectorListNodeFactory = new TopSelectorListNodeFactory();

    SelectorNode selectorNode1 = new SelectorNode();
    SelectorNode selectorNode2 = new SelectorNode();
    @Before
    public void before(){
        priorityMap.put("s",2f);
        priorityMap.put("g",1f);
        selectorNode1.setNodeInfo(StreamFactory.NODE_NAME,"g");
        selectorNode2.setNodeInfo(StreamFactory.NODE_NAME,"s");
        ruleSorter.addRule(selectorNode1);
        ruleSorter.addRule(selectorNode2);
    }

    /**
     * 参数正常情况
     * @throws Exception
     */
    public void testAssembleNodeList() throws Exception {
        assertNotNull(topSelectorListNodeFactory.assembleNodeList(new SelectorNode(),ruleSorter));
    }

    /**
     * 参数异常情况
     * @throws Exception
     */
    public void testAssembleNodeListException1() throws Exception {
        assertNull(topSelectorListNodeFactory.assembleNodeList(new SelectorNode(), null));
    }

    /**
     * 参数异常情况
     * @throws Exception
     */
    public void testAssembleNodeListExceptionException2() throws Exception {
        assertNull(topSelectorListNodeFactory.assembleNodeList(null,ruleSorter));
    }

    /**
     * 正常构造ListNode
     * @throws Exception
     */
    public void testCreateListNode() throws Exception {
        assertNotNull(topSelectorListNodeFactory.createListNode(ruleSorter));
    }

    /**
     * 异常构造ListNode
     * @throws Exception
     */
    public void testCreateListNodeException() throws Exception {
        assertNull(topSelectorListNodeFactory.createListNode(null));
    }
}