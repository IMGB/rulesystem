package imgb.rulesystem.factory.nodefactory.leaf;

import imgb.rulesystem.exception.FactoryException;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.rulereader.token.RuleToken;

/**
 * Created by is on 1/13/15.
 */
public abstract class LeafNodeFactory {
    public abstract BaseNode createNode(RuleToken token) throws FactoryException;
}
