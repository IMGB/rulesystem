package imgb.rulesystem.node.list;


import org.junit.Test;
import imgb.rulesystem.context.Context;

import static org.junit.Assert.*;

public class SequenceNodeTest {

    @Test
    public void testExecute() throws Exception {
        UserForTest user = new UserForTest("2");

        NodeListImp userConditionNode = new NodeListImp(user);

        Context context = new Context(null);
        context.setRecord(Context.LAST_RULE_EXECUTED_NODE,user);

        SequenceNode sequenceNode = new SequenceNode();
        sequenceNode.addSubNode(userConditionNode);
        boolean result = sequenceNode.execute(context);
        assertEquals(true,result);
    }
}