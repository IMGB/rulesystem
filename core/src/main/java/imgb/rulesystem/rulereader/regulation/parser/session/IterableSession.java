package imgb.rulesystem.rulereader.regulation.parser.session;

import imgb.rulesystem.rulereader.regulation.annotaion.IterableToken;
import imgb.rulesystem.rulereader.token.NullToken;
import imgb.rulesystem.rulereader.token.RegulationToken;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Daniel on 2014/10/10.
 */
public class IterableSession extends Session {
    private Iterator iterator;
    private IterableToken iterableToken;
    public IterableSession(Iterable iterable, IterableToken iterableToken, Stack<Session> sessions) {
        super(sessions);
        this.iterator = iterable.iterator();
        this.iterableToken = iterableToken;
        if(!iterableToken.tagName().equals("")) {
            beginSession = new RegulationToken(iterableToken.tagName(), "begin",false);
            endSession = new RegulationToken(iterableToken.tagName(), "end",true);
        }
    }

    @Override
    public RegulationToken getNextAvailableToken() {
        if(iterableToken.subSession()) {
            return getSubSessionToken();
        }
        return getNormalToken();
    }

    private RegulationToken getSubSessionToken() {
        while(iterator.hasNext()) {
            Session session = new ObjectSession(iterator.next(), stack, iterableToken.tokenName());
            stack.push(session);
            RegulationToken returnToken = session.getNextToken();
            if(returnToken != null) {
                return returnToken;
            }
            stack.pop();
        }
        return null;
    }

    private RegulationToken getNormalToken() {
        if(iterator.hasNext()) {
            Object token = iterator.next();
            if(token != null){
                return new RegulationToken(iterableToken.tokenName() ,token.toString() ,false);
            }
            return new NullToken();
        }
        return null;
    }
}
