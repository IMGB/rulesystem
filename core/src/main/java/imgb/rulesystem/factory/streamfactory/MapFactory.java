package imgb.rulesystem.factory.streamfactory;


import imgb.rulesystem.factory.nodefactory.leaf.LeafNodeFactory;
import imgb.rulesystem.rulereader.reader.RuleReader;
import org.apache.log4j.Logger;
import imgb.rulesystem.exception.FactoryException;
import imgb.rulesystem.factory.nodefactory.MapNodeFactory;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.rulereader.token.RuleToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 2014/9/6.
 * 获得token 对应节点工厂 的映射器
 */
public class MapFactory {

    private static final Logger LOGGER = Logger.getLogger(MapFactory.class);

    private Map<String, StreamFactory> streamFactoryMap;
    private MapNodeFactory mapNodeFactory;

    /**
     *
     * @param streamFactoryMap tokenname 和 StreamFactory 的映射关系
     * @param mapNodeFactory tokenname 和 叶节点工厂的隐射关系
     */
    public MapFactory(Map<String, StreamFactory> streamFactoryMap, MapNodeFactory mapNodeFactory) {
        super();
        this.streamFactoryMap = streamFactoryMap;
        this.mapNodeFactory = mapNodeFactory;
    }

    public MapFactory() {
        streamFactoryMap = new HashMap<>();
        mapNodeFactory = new MapNodeFactory();
    }

    protected MapFactory putStreamFactory(String tokenName, StreamFactory streamFactory) {
        streamFactoryMap.put(tokenName, streamFactory);
        return this;
    }

    protected MapFactory putNodeFactory(String tokenName, LeafNodeFactory nodeFactory) {
        mapNodeFactory.put(tokenName, nodeFactory);
        return this;
    }

    /**
     * 获得当前的token 对应的工厂 并利用这些工厂获得最终生产的节点
     * @param currentToken
     * @param reader
     * @return
     * @throws FactoryException
     */
    public BaseNode createNode(RuleToken currentToken, RuleReader reader) throws FactoryException {
        BaseNode returnNode = getFromStreamFacotry(currentToken, reader);
        if (returnNode == null) {
            returnNode = mapNodeFactory.createNode(currentToken);
        }
        return returnNode;
    }

    private BaseNode getFromStreamFacotry(RuleToken nowToken, RuleReader reader) throws FactoryException {
        StreamFactory factory = streamFactoryMap.get(nowToken.getTokenName());
        if (factory == null) {
            return null;
        }
        return factory.createNode(reader);
    }
}
