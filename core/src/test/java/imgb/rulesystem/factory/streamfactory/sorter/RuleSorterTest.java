package imgb.rulesystem.factory.streamfactory.sorter;

import org.junit.Before;
import org.junit.Test;
import imgb.rulesystem.factory.streamfactory.StreamFactory;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.node.list.SelectorNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RuleSorterTest {
    Map<String, Integer> priorityMap = new HashMap<String,Integer>();
    PriorityManager priorityManager = new PriorityManager(priorityMap);

    SelectorNode selectorNode1 = new SelectorNode();
    SelectorNode selectorNode2 = new SelectorNode();
    @Before
    public void before(){
        priorityMap.put("s",2);
        priorityMap.put("g",1);
        selectorNode1.setNodeInfo(StreamFactory.NODE_NAME,"g");
        selectorNode2.setNodeInfo(StreamFactory.NODE_NAME,"s");
    }

    @Test
    public void testGetSortedNodes() throws Exception {
        RuleSorter ruleSorter = new RuleSorter(priorityManager);
        List<BaseNode> list = new ArrayList<BaseNode>();

        ruleSorter.addRule(selectorNode1);
        ruleSorter.addRule(selectorNode2);
        list = ruleSorter.getSortedNodes();
        for (BaseNode baseNode : list){
            System.out.println(priorityManager.getPriority(baseNode));
        }

        assertNotNull(list);
    }
}