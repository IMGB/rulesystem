package imgb.rulesystem.node.list;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.log4j.Logger;
import imgb.rulesystem.node.BaseNode;

import java.util.ArrayList;

/**
 * NodeList is the base class of list-Node
 * <p>NodeList is a node which has children nodes.
 * When invoke execute function,it will execute the sub-node in order.
 *
 * @param
 * @see
 */
public abstract class NodeList extends BaseNode {
    private static final Logger logger = Logger.getLogger(NodeList.class);
    protected ArrayList<BaseNode> childrenNode = new ArrayList<BaseNode>();


    /**
     * default construct function
     * <p>create a ArrayList for child node containing
     */
    public NodeList() {

    }

    /**
     * this get a ArrayList container the child list
     *
     * @return a array list
     */
    public ArrayList<BaseNode> getChildren() {
        return childrenNode;
    }

    /**
     * add child-node to the list end
     *
     * @param node node to add as a child node.In most situation,it's action node or condition node
     */
    public void addSubNode(BaseNode node) {
        childrenNode.add(node);
    }


    /**
     * Adds the specified item to the the scrolling list at the position indicated by the index.
     * The index is zero-based.
     * If the value of the index is less than zero,
     * or if the value of the index is greater than or equal to the number of items in the list,
     * then the item is added to the end of the list.
     *
     * @param node
     * @param index
     */
    public void addSubNode(BaseNode node, int index) {
        childrenNode.add(index, node);
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * * @param index
     */
    public void deleteSubNode(int index) {
        childrenNode.remove(index);
    }

    /**
     * get nodes count
     *
     * @return sub nodes count
     */
    public int getNodesCount() {
        return childrenNode.size();
    }


    @Override
    public JsonNode serlizationToJson() {
            ArrayNode arrayNode = OBJECT_MAPPER.createArrayNode();
            ObjectNode rootNode = OBJECT_MAPPER.createObjectNode();
            rootNode.put("class", this.getClass().getName());

            for(BaseNode node :getChildren()) {
                if(node == null) {
                    return null;
                }
                arrayNode.add(node.serlizationToJson());
            }
            rootNode.put("value", arrayNode);
            return rootNode;
    }
    
    @Override
    public BaseNode deserializeFromJson(JsonNode root) throws Exception {

		JsonNode jsonNode = root.get("value");
		if (jsonNode == null) {
			throw new Exception("the value field doesn't exist");
		}
		if (jsonNode.isArray()) {
			for (final JsonNode arrNode : jsonNode) {
				String className = arrNode.get("class").asText();
				BaseNode nextNode = (BaseNode) Class.forName(className).newInstance();
				nextNode.deserializeFromJson(arrNode);
				this.childrenNode.add(nextNode);
			}
		} else {
			throw new Exception(
					"currently all node instances of subclass of NodeList should have the value as array");
		}
		return this;
    }
    

}
