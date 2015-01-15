package imgb.rulesystem.factory.nodefactory;

import imgb.rulesystem.factory.nodefactory.MapNodeFactory;
import imgb.rulesystem.factory.nodefactory.leaf.LeafNodeFactory;
import imgb.rulesystem.factory.streamfactory.UserConditionFactroyForTest;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.rulereader.token.RegulationToken;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MapNodeFactoryTest {
    Map<String, LeafNodeFactory> nodeMap = new HashMap<String,LeafNodeFactory>();
    MapNodeFactory mapNodeFactory = new MapNodeFactory(nodeMap);
    RegulationToken regulationToken = new RegulationToken("test","12",true);
    @Before
    public void before(){
        UserConditionFactroyForTest userConditionFactroyForTest = new UserConditionFactroyForTest();
        nodeMap.put("test",userConditionFactroyForTest);
    }

    /**
     * 正常输入，返回baseNode
     * @throws Exception
     */
    @Test
    public void testCreateNode() throws Exception {
        BaseNode baseNode = mapNodeFactory.createNode(regulationToken);
        System.out.println(baseNode);
        assertNotNull(baseNode);
    }

    /**
     * 输入为空，返回为空
     * @throws Exception
     */
    @Test
    public void testCreateNodeError() throws Exception {
        BaseNode baseNode = mapNodeFactory.createNode(null);
        System.out.println(baseNode);
        assertNull(baseNode);
    }

}