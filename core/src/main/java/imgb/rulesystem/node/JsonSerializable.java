package imgb.rulesystem.node;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
