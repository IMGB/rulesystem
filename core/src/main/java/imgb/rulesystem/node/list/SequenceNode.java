package imgb.rulesystem.node.list;


import imgb.rulesystem.context.Context;
import imgb.rulesystem.node.exception.NodeExecutionException;
import imgb.rulesystem.node.BaseNode;

/**
 * this function execute the child-nodes in order and return false until someone
 * child node return false; if no child node return false;it return true
 *
 * @param
 * @return
 */
public class SequenceNode extends NodeList {
	public boolean execute(Context context) throws NodeExecutionException {
		boolean result = true;
		for (BaseNode node : this.getChildren()) {
			result = node.execute(context);
			if (!result) {
				break;
			}
		}
		return result;
	}

}