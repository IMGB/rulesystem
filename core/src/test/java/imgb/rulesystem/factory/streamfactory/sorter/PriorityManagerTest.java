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

    /**
     * 通过key值获得priorityMap 中所对应的key值
     * @return
     */
    @Test
    public void testGetPriority() throws Exception {
        Float priority = priorityManager.getPriority("g");
        System.out.print(priority);
        assertEquals(Float.valueOf(1f), priority);
    }



    /**
     * 通过节点获得当前节点的优先值
     * @return
     */
    @Test
    public void testGetPriority1() throws Exception {
        Float priority = priorityManager.getPriority(selectorNode);
        System.out.print(priority);
        assertEquals(Float.valueOf(1f), priority);
    }

    @Test
    public void testGetPriorityError1() throws Exception {
        Float priority = priorityManager.getPriority(new SelectorNode());
        System.out.print(priority);
        assertEquals(Float.valueOf(0f), priority);
    }
}