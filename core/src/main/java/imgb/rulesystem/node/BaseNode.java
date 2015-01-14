package imgb.rulesystem.node;

import imgb.rulesystem.context.Context;
import imgb.rulesystem.node.exception.NodeExecutionException;
import imgb.rulesystem.node.information.nodeinfo.NodeInfo;
import imgb.rulesystem.node.exception.InvalidJsonNodeKeyException;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.w3c.dom.NodeList;

public abstract class BaseNode implements JsonSerializable {

	private static final Logger logger = Logger.getLogger(NodeList.class);

	protected NodeInfo nodeInfo;

	public abstract boolean execute(Context context) throws NodeExecutionException;

	public Object getNodeInfo(String key) {
		if (nodeInfo == null) {
			return null;
		}
		return nodeInfo.getInfo(key);
	}

	public void setNodeInfo(String key, Object value) {
		if (nodeInfo == null) {
			nodeInfo = new NodeInfo();
		}
		nodeInfo.setInfo(key, value);
	}

	public BaseNode() {

	}

	/**
	 * json序列化接口，使用当前的node反序列化为对象
	 * 
	 * @return the root node of the whole tree
	 */
	public static BaseNode deserialization(String json) throws Exception {
		JsonNode root = OBJECT_MAPPER.readTree(json);
		JsonNode rootClass = root.get("class");
		if (rootClass == null) {
			throw new InvalidJsonNodeKeyException("class field not exists");
		} else {
			String className = root.get("class").asText();
			try {
				BaseNode rootNode = (BaseNode) Class.forName(className).newInstance();
				return (BaseNode) rootNode.deserializeFromJson(root);
			} catch (ClassNotFoundException ex) {
				logger.error("class name is invalid: " + className);
				return null;
			}
		}
	}
}
