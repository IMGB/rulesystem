package imgb.rulesystem.factory.streamfactory;


import imgb.rulesystem.factory.nodefactory.leaf.LeafNodeFactory;
import org.junit.Test;
import imgb.rulesystem.factory.nodefactory.MapNodeFactory;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.rulereader.token.RegulationToken;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;


public class MapFactoryTest {

    @Test()
    public void testCreateNode() throws Exception {
        RegulationToken regulationToken = new RegulationToken("map","12",true);

        Map<String, LeafNodeFactory> constructMap = new HashMap<String, LeafNodeFactory>();
        MapNodeFactory mapNodeFactory = new MapNodeFactory(constructMap);
        constructMap.put("map",null);

        Map map  = new HashMap();
        MapFactory mapFactory = new MapFactory(map,mapNodeFactory);

        BaseNode baseNode = mapFactory.createNode(regulationToken,null);

        System.out.println(baseNode);
        assertNotNull(baseNode);

    }
}