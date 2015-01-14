package imgb.rulesystem.factory.streamfactory.sorter;

import imgb.rulesystem.node.list.SelectorNode;
import org.junit.Before;
import org.junit.Test;
import imgb.rulesystem.factory.streamfactory.StreamFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PriorityManagerTest {
    Map<String, Float> priorityMap = new HashMap<>();
    PriorityManager priorityManager = new PriorityManager(priorityMap);
    SelectorNode selectorNode = new SelectorNode();
    @Before
    public void before(){
        priorityMap.put("g",1f);
        priorityMap.put("s",2f);
        selectorNode.setNodeInfo(StreamFactory.NODE_NAME,"g");
    }

    @Test
    public void testGetPriority() throws Exception {
        Float priority = priorityManager.getPriority("g");
        System.out.print(priority);
        assertEquals(Float.valueOf(1f), priority);
    }

    @Test
    public void testGetPriority1() throws Exception {
        Float priority = priorityManager.getPriority(selectorNode);
        System.out.print(priority);
        assertEquals(Float.valueOf(1f), priority);
    }
}