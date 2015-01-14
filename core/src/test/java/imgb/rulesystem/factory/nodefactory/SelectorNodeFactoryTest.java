package imgb.rulesystem.factory.nodefactory;

import imgb.rulesystem.factory.nodefactory.list.SelectorNodeFactory;
import imgb.rulesystem.node.list.NodeList;
import org.junit.Before;
import org.junit.Test;
import imgb.rulesystem.factory.streamfactory.StreamFactory;
import imgb.rulesystem.factory.streamfactory.sorter.PriorityManager;
import imgb.rulesystem.factory.streamfactory.sorter.RuleSorter;
import imgb.rulesystem.node.list.SelectorNode;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SelectorNodeFactoryTest {
    Map<String, Float> priorityMap = new HashMap<>();
    PriorityManager priorityManager = new PriorityManager(priorityMap);
    RuleSorter ruleSorter = new RuleSorter(priorityManager);

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

    @Test
    public void testCreateListNode() throws Exception {
        SelectorNodeFactory selectorNodeFactory = new SelectorNodeFactory();
        NodeList nodeList = selectorNodeFactory.createListNode(ruleSorter);
        System.out.println(nodeList.getNodesCount()+nodeList.toString());
        assertNotNull(nodeList);
    }
}