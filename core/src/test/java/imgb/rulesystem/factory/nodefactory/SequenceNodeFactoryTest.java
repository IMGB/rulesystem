package imgb.rulesystem.factory.nodefactory;

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
    Map<String, Integer> priorityMap = new HashMap<String,Integer>();
    PriorityManager priorityManager = new PriorityManager(priorityMap);
    RuleSorter ruleSorter = new RuleSorter(priorityManager);

    SequenceNode sequenceNode1 = new SequenceNode();
    SequenceNode sequenceNode2 = new SequenceNode();
    @Before
    public void before(){
        priorityMap.put("s",2);
        priorityMap.put("g",1);
        sequenceNode1.setNodeInfo(StreamFactory.NODE_NAME,"g");
        sequenceNode2.setNodeInfo(StreamFactory.NODE_NAME,"s");
        ruleSorter.addRule(sequenceNode1);
        ruleSorter.addRule(sequenceNode2);
    }

    @Test
    public void testCreateListNode() throws Exception {
        SequenceNodeFactory sequenceNodeFactory = new SequenceNodeFactory();
        NodeList nodeList = sequenceNodeFactory.createListNode(ruleSorter);
        System.out.println(nodeList.getNodesCount()+nodeList.toString());
        assertNotNull(nodeList);
    }
}