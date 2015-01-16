package imgb.rulesystem.factory.nodefactory.list;

import imgb.rulesystem.factory.nodefactory.list.SequenceNodeFactory;
import imgb.rulesystem.factory.streamfactory.sorter.RuleSorter;
import imgb.rulesystem.node.list.NodeList;
import imgb.rulesystem.node.list.SequenceNode;
import org.junit.Before;
import org.junit.Test;
import imgb.rulesystem.factory.streamfactory.StreamFactory;
import imgb.rulesystem.factory.streamfactory.sorter.PriorityManager;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SequenceNodeFactoryTest {
    Map<String, Float> priorityMap = new HashMap<>();
    PriorityManager priorityManager = new PriorityManager(priorityMap);
    RuleSorter ruleSorter = new RuleSorter(priorityManager);

    SequenceNode sequenceNode1 = new SequenceNode();
    SequenceNode sequenceNode2 = new SequenceNode();
    @Before
    public void before(){
        priorityMap.put("s",2f);
        priorityMap.put("g",1f);
        sequenceNode1.setNodeInfo(StreamFactory.NODE_NAME,"g");
        sequenceNode2.setNodeInfo(StreamFactory.NODE_NAME,"s");
        ruleSorter.addRule(sequenceNode1);
        ruleSorter.addRule(sequenceNode2);
    }

    /**
     * 正常测试。返回Nodelist对象
     * @throws Exception
     */
    @Test
    public void testCreateListNode() throws Exception {
        SequenceNodeFactory sequenceNodeFactory = new SequenceNodeFactory();
        NodeList nodeList = sequenceNodeFactory.createListNode(ruleSorter);
        assertNotNull(nodeList);
    }

    /**
     * 输入为空，返回空值。
     * @throws Exception
     */
    @Test
    public void testCreateListNodeNull() throws Exception {
        SequenceNodeFactory sequenceNodeFactory = new SequenceNodeFactory();
        NodeList nodeList = sequenceNodeFactory.createListNode(null);
        assertNull(nodeList);
    }
}