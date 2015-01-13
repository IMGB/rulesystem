package imgb.rulesystem.factory.nodefactory;

/**
 * Created by Daniel on 2014/9/14.
 */

import imgb.rulesystem.exception.FactoryException;
import imgb.rulesystem.factory.nodefactory.leaf.LeafNodeFactory;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.rulereader.token.RuleToken;

import java.util.Map;

/**
 * Created by ca on 8/13/14.<br>
 * the factory create node from factory
 */
public class MapNodeFactory {

    private Map<String, LeafNodeFactory> nodeMap;

    public MapNodeFactory(Map<String, LeafNodeFactory> constructionMap) {
        nodeMap = constructionMap;
    }

    /**
     * create the node from nodeInfo
     *
     * @param token
     * @return if basenode can not created then return null,otherwhise return the node object
     * @throws
     */
    public BaseNode createNode(RuleToken token) throws FactoryException {
        if (token == null) {
            return null;
        }
        String tokenName = token.getTokenName();
        Object object = nodeMap.get(tokenName);

        if (object == null) {
            return null;
        }

        BaseNode returnNode = createNodeFromFactory(object, token);
        return returnNode;
    }

    /**
     * create a basenode from the factory object
     *
     * @param factoryObject a instance of IFactory or member of IFactory
     * @param
     * @return if basenode can not created then return null,otherwhise return the node object
     */
    protected BaseNode createNodeFromFactory(Object factoryObject, RuleToken token) throws FactoryException {
        if (!(factoryObject instanceof LeafNodeFactory)) {
            return null;
        }
        LeafNodeFactory factory = (LeafNodeFactory) factoryObject;
        return factory.createNode(token);

    }

}
