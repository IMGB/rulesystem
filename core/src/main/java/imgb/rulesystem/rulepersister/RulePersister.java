package imgb.rulesystem.rulepersister;

import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.rulepersister.exception.PersistException;

/**
 * Created by is on 1/14/15.
 */
public abstract class RulePersister {
    public abstract void persist(BaseNode baseNode) throws PersistException;
}
