package imgb.rulesystem.factory.streamfactory;

import imgb.rulesystem.factory.nodefactory.leaf.LeafNodeFactory;
import imgb.rulesystem.factory.nodefactory.list.SelectorNodeFactory;
import imgb.rulesystem.factory.nodefactory.MapNodeFactory;
import org.junit.Test;
import imgb.rulesystem.factory.streamfactory.sorter.PriorityManager;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.rulereader.token.RegulationToken;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SortStreamFactoryTest {

    private MapFactory getMapFactory(){
        RegulationToken regulationToken = new RegulationToken("user","2",true);

        UserConditionFactroyForTest userConditionFactroyTest = new UserConditionFactroyForTest();

        Map<String, LeafNodeFactory> constructionMap = new HashMap<String, LeafNodeFactory>();
        constructionMap.put("STREAM_FACTORY_PRIORITY_KEY",userConditionFactroyTest);

        MapNodeFactory mapNodeFactory = new MapNodeFactory(constructionMap);

        return new MapFactory(new HashMap<>(),mapNodeFactory);

    }

    private PriorityManager getPriorityManager(){
        Map<String, Float> map = new HashMap<>();
        map.put("user",1f);

        PriorityManager priorityManager = new PriorityManager(map);

        return priorityManager;
    }

    @Test
    public void testCreateNode() throws Exception {

        SelectorNodeFactory selectorNodeFactory = new SelectorNodeFactory();

        SortStreamFactory sortStreamFactory = new SortStreamFactory(getPriorityManager(),getMapFactory(),selectorNodeFactory);

        BaseNode baseNode = sortStreamFactory.createNode(new RuleReaderImp());

        System.out.println(baseNode);

        assertNotNull(baseNode);

    }
}