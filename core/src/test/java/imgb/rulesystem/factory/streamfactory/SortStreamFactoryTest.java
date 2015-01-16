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

    /**
     * 参数正常情况
     * @throws Exception
     */
    @Test
    public void testCreateNode() throws Exception {
        SelectorNodeFactory selectorNodeFactory = new SelectorNodeFactory();
        SortStreamFactory sortStreamFactory = new SortStreamFactory(getPriorityManager(),getMapFactory(),selectorNodeFactory);
        BaseNode baseNode = sortStreamFactory.createNode(new RuleReaderImp());
        assertNotNull(baseNode);
    }

    /**
     * 参数异常情况
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testCreateNodeException() throws Exception {
        SortStreamFactory sortStreamFactory = new SortStreamFactory(null,null,null);
        BaseNode baseNode = sortStreamFactory.createNode(new RuleReaderImp());
        assertNull(baseNode);
    }

    /**
     * 参数异常情况
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testCreateNodeException2() throws Exception {
        SortStreamFactory sortStreamFactory = new SortStreamFactory(getPriorityManager(),getMapFactory(),null);
        BaseNode baseNode = sortStreamFactory.createNode(new RuleReaderImp());
        assertNull(baseNode);
    }

    /**
     * 参数异常情况
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testCreateNodeException3() throws Exception {
        SelectorNodeFactory selectorNodeFactory = new SelectorNodeFactory();
        SortStreamFactory sortStreamFactory = new SortStreamFactory(getPriorityManager(),null,selectorNodeFactory);
        BaseNode baseNode = sortStreamFactory.createNode(new RuleReaderImp());
        assertNull(baseNode);
    }

    /**
     * 参数异常情况
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testCreateNodeException4() throws Exception {
        SelectorNodeFactory selectorNodeFactory = new SelectorNodeFactory();
        SortStreamFactory sortStreamFactory = new SortStreamFactory(null,getMapFactory(),selectorNodeFactory);
        BaseNode baseNode = sortStreamFactory.createNode(new RuleReaderImp());
        assertNull(baseNode);
    }
}