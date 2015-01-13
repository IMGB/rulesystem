package imgb.rulesystem.rulecontainer;

import imgb.rulesystem.context.Context;
import imgb.rulesystem.node.exception.NodeExecutionException;
import imgb.rulesystem.rulecontainer.data.NodesData;
import imgb.rulesystem.node.BaseNode;
import imgb.rulesystem.rulecontainer.exception.RuleRunException;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by ca on 10/20/14.<br>
 */
public class RuleContainer {
    private static final Logger LOGGER = Logger.getLogger(RuleContainer.class);

    private NodesData data;

    public RuleContainer(NodesData data) {
        this.data = data;
    }

    public boolean addRule(BaseNode node, Long msTime) {
        return data.insert(msTime, node);
    }

    public void clearAll() {
        data.clear();
    }

    protected BaseNode getRuleNode(Long msTime) {
        return data.search(msTime);
    }

    protected Context executeBaseNode(Context context, BaseNode node) throws RuleRunException{
        if(node != null) {
            return context;
        }
        try {
            if(!node.execute(context)) {
                LOGGER.warn("node return false");
            }
        } catch (NodeExecutionException e) {
            throw new RuleRunException(e);
        }
        return context;
    }


    public Context run(Context context) throws RuleRunException{
        BaseNode node = getRuleNode(System.currentTimeMillis());
        return executeBaseNode(context, node);
    }

    public Context run(Context context, Date date) throws RuleRunException{
        BaseNode node = getRuleNode(date.getTime());
        return executeBaseNode(context, node);
    }

}
