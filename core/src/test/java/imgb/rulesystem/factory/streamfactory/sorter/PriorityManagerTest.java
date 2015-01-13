package imgb.rulesystem.factory.streamfactory.sorter;

import imgb.rulesystem.node.list.SelectorNode;
import org.junit.Before;
import org.junit.Test;
import imgb.rulesystem.factory.streamfactory.StreamFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PriorityManagerTest {
    Map<String, Integer> priorityMap = new HashMap<String,Integer>();
    PriorityManager priorityManager = new PriorityManager(priorityMap);
    SelectorNode selectorNode = new SelectorNode();
    @Before
    public void before(){
        priorityMap.put("g",1);
        priorityMap.put("s",2);
        selectorNode.setNodeInfo(StreamFactory.NODE_NAME,"g");
    }

    @Test
    public void testGetPriority() throws Exception {
        Integer priority = priorityManager.getPriority("g");
        System.out.print(priority);
        assertEquals(Integer.valueOf(1), priority);
    }

    @Test
    public void testGetPriority1() throws Exception {
        Integer priority = priorityManager.getPriority(selectorNode);
        System.out.print(priority);
        assertEquals(Integer.valueOf(1), priority);
    }
}