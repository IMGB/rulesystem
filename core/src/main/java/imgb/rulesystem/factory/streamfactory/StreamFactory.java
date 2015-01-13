package imgb.rulesystem.factory.streamfactory;


import imgb.rulesystem.exception.FactoryException;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.rulereader.reader.RuleReader;

/**
 * Created by ca on 9/4/14.<br>
 *     流式规则构造器
 */
public abstract class StreamFactory {

    public static final String PRIORITY_KEY = "STREAM_FACTORY_PRIORITY_KEY";
    public static final String NODE_NAME = "STREAM_FACTORY_NODE_NAME";

    public StreamFactory() {
        super();
    }

    /**
     *
     * @param reader 通过reader 获得流式tokens值 然后构造出当前的节点
     * @return
     * @throws imgb.rulesystem.exception.FactoryException
     */
    public abstract BaseNode createNode(RuleReader reader) throws FactoryException;
}
