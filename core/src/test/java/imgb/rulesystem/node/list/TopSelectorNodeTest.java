package imgb.rulesystem.node.list;

import org.junit.Test;
import imgb.rulesystem.context.Context;

import static org.junit.Assert.*;

public class TopSelectorNodeTest {

    @Test
    public void testExecute() throws Exception {
        UserForTest user = new UserForTest("2");

        NodeListImp userConditionNode = new NodeListImp(user);

        Context context = new Context(null);
        context.setRecord(Context.LAST_RULE_EXECUTED_NODE,user);

        TopSelectorNode topSelectorNode = new TopSelectorNode();
        topSelectorNode.addSubNode(userConditionNode);
        assertEquals(true,topSelectorNode.execute(context));

    }
}