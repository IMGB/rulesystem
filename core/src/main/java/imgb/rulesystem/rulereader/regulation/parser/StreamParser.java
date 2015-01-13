package imgb.rulesystem.rulereader.regulation.parser;

import imgb.rulesystem.rulereader.regulation.parser.session.ObjectSession;
import imgb.rulesystem.rulereader.regulation.parser.session.Session;
import imgb.rulesystem.rulereader.token.RuleToken;

import java.util.Stack;

/**
 * Created by ca on 10/10/14.<br>
 */
public class StreamParser {
    private Stack<Session> stack = new Stack<Session>();

    private RuleToken tempToken = null;

    public StreamParser (Object object, String objectTokenName) {
        Session session = new ObjectSession(object, stack, objectTokenName);
        stack.push(session);
    }

    private RuleToken getNextToken() {
        while(!stack.empty()){
            Session session = stack.peek();
            RuleToken ruleToken = session.getNextToken();
            if(ruleToken != null) {
                return ruleToken;
            }
            stack.pop();
        }
        return null;
    }

    public RuleToken getNext() {
        RuleToken returnToken = tempToken;
        if(returnToken == null) {
            returnToken = getNextToken();
        }
        tempToken = null;
        return  returnToken;
    }

    public boolean hasNext() {
        if(tempToken != null) {
            return true;
        }
        tempToken = getNextToken();
        return tempToken != null;
    }
}
