package imgb.rulesystem.node.list;


import imgb.rulesystem.context.Context;
import imgb.rulesystem.node.exception.NodeExecutionException;
import imgb.rulesystem.node.BaseNode;

/**
 * the selector in behavior tree
 */
public class SelectorNode extends NodeList {
	/**
	 * this function execute the child-nodes in order and return true until
	 * someone child node return true; if no child node return true;it return
	 * false
	 *
	 * @param context
	 * @return
	 * @throw                                                                                                                                        s
	 */
	public boolean execute(Context context) throws NodeExecutionException {
		boolean result = false;
		for (BaseNode node : this.getChildren()) {
			result = node.execute(context);
			if (result) {
				break;
			}
		}
		return result;
	}

}