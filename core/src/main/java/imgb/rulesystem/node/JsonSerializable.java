package imgb.rulesystem.node;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public interface JsonSerializable {
	/**
	 * json序列化接口，将当前类转化为jsonnode
	 * 
	 * @return 返回本对象json序列化之后的jsonnode
	 */
	public JsonNode serlizationToJson();

	public Object deserializeFromJson(JsonNode jsonNode) throws Exception;

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}
