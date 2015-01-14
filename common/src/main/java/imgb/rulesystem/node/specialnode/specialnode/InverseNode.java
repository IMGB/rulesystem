package imgb.rulesystem.node.specialnode.specialnode;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import imgb.rulesystem.context.Context;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.node.exception.NodeExecutionException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ca on 9/4/14.<br>
 *     反向节点
 */
public class InverseNode extends BaseNode {

    private BaseNode subNode;

    public InverseNode(BaseNode node) {
        super();
        subNode = node;
    }

    /**
     * @param context
     * @return
     * @throws NodeExecutionException when the subNode did not exit or subNode execute with RebateNodeException
     */
    public boolean execute(Context context) throws NodeExecutionException {
        if (subNode == null) {
            throw new NodeExecutionException("subNode did not exist");
        }
        return subNode.execute(context);
    }

    @Override
    public JsonNode serlizationToJson() {
        ObjectNode node = OBJECT_MAPPER.createObjectNode();
        node.put("class", this.getClass().getName());
        node.set("value", subNode.serlizationToJson());
        return node;
    }
    
	@Override
	public InverseNode deserializeFromJson(JsonNode root) throws Exception {
		JsonNode valueNode=root.get("value");
		JsonNode classNode=valueNode.get("class");
		assert classNode!=null;
		BaseNode bn=(BaseNode)Class.forName(classNode.asText()).newInstance();
		bn.deserializeFromJson(valueNode);
		this.subNode=bn;
		return this;
	}

    /**
     * get descrption by subNode'sgetDescrption;
     *
     * @return
     */
    public String toString() {
        try{
            Map<String, String> map = new HashMap<>();
            map.put(this.getClass().getSimpleName(), subNode.toString());
            StringWriter sw = new StringWriter();
            OBJECT_MAPPER.writer().writeValue(sw, map);
            return sw.toString();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
