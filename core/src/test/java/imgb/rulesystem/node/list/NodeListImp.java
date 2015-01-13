package imgb.rulesystem.node.list;

import imgb.rulesystem.node.JsonSerializable;
import imgb.rulesystem.context.Context;
import imgb.rulesystem.node.exception.NodeExecutionException;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.node.exception.InvalidJsonNodeKeyException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created by is on 15/1/9.
 */
public class NodeListImp extends BaseNode {
    private UserForTest user;

    public UserForTest getUser() {
        return user;
    }

    public NodeListImp(UserForTest user) {
        this.user = user;
    }

    public NodeListImp() {
    }

    /**
     * 检测属性user和Context中当前存储的User对象时否一致 一致返回true，否则返回false
     *
     * @param context
     *            上下文环境对象
     * @return 返回执行结果
     * @throws imgb.rulesystem.node.exception.NodeExecutionException
     */
    @Override
    public boolean execute(Context context) throws NodeExecutionException {
        if (user == null) {
            return false;
        }
        UserForTest currentUser = (UserForTest) context.getRecord(Context.LAST_RULE_EXECUTED_NODE);
        if (currentUser == null) {
            throw new NodeExecutionException("can not get current user");
        }
        return user.getId().equals(currentUser.getId());
    }

    @Override
    public JsonNode serlizationToJson() {
        ObjectNode rootNode = JsonSerializable.OBJECT_MAPPER.createObjectNode();
        ObjectNode valueJson = JsonSerializable.OBJECT_MAPPER.createObjectNode();
        rootNode.put("class", this.getClass().getName());
        rootNode.put("value", valueJson);
        String id = "";
        if (user != null && user.getId() != null) {
            id = user.getId().toString();
        }
        valueJson.put("product_category_id", id);
        return rootNode;
    }

    @Override
    public Object deserializeFromJson(JsonNode root) throws Exception {
        JsonNode valueNode = root.get("value");
        JsonNode productIdNode = valueNode.get("product_category_id");
        if (productIdNode == null) {
            throw new InvalidJsonNodeKeyException("product_category_id is not valid");
        }
        String id = productIdNode.asText();
        assert !id.isEmpty();
        UserForTest u = new UserForTest(id);
        this.user = u;
        return this;
    }
}
