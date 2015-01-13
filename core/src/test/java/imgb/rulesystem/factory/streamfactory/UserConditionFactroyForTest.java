package imgb.rulesystem.factory.streamfactory;


import imgb.rulesystem.exception.FactoryException;
import imgb.rulesystem.factory.nodefactory.leaf.ConditionNodeFactory;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.node.list.SelectorNode;
import imgb.rulesystem.rulereader.token.RuleToken;

/**
 * Created by ca on 8/29/14.<br>
 */
public class UserConditionFactroyForTest extends ConditionNodeFactory {
    public static final String USER_TOKEN_NAME = "USER_TOKEN_NAME";

    public BaseNode createNode(RuleToken token) throws FactoryException {
        String value = token.getTokenString();

        if (value.equals("*")) {
            return null;
        }

        return new SelectorNode();
    }
}
