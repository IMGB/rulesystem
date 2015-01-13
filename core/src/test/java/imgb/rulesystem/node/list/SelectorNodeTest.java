package imgb.rulesystem.node.list;

import org.junit.Test;
import imgb.rulesystem.context.Context;

import static org.junit.Assert.*;

public class SelectorNodeTest {

    @Test
    public void testExecute() throws Exception {

        UserForTest user = new UserForTest("2");

        NodeListImp userConditionNode = new NodeListImp(user);

        Context context = new Context(null);
        context.setRecord(Context.LAST_RULE_EXECUTED_NODE,user);

        SelectorNode selectorNode = new SelectorNode();
        selectorNode.addSubNode(userConditionNode);
        boolean result = selectorNode.execute(context);
        assertEquals(true,result);

    }
}