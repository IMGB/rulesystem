package imgb.rulesystem.rulereader.regulation.parser.session;

import imgb.rulesystem.rulereader.regulation.annotaion.TokenRegulation;
import imgb.rulesystem.rulereader.token.NullToken;
import imgb.rulesystem.rulereader.token.RegulationToken;
import imgb.rulesystem.rulereader.regulation.annotaion.IterableToken;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by ca on 10/10/14.<br>
 */
public class  ObjectSession extends Session{

    private static final Logger logger = Logger.getLogger(ObjectSession.class);

    private Iterator<Method> methodIterator = null;

    private Object regularObject;

    public ObjectSession(Object object, Stack<Session> stack, String sessionName) {
        super(stack);
        regularObject = object;
        endSession = new RegulationToken(sessionName, "end", true);
        beginSession = new RegulationToken(sessionName, "begin", false);
        methodIterator = Arrays.asList(object.getClass().getMethods()).iterator();
    }

    @Override
    public RegulationToken getNextAvailableToken() {
        try{
            while (methodIterator.hasNext()) {
                RegulationToken regulationToken = inovkeMethod(methodIterator.next());
                if(regulationToken != null) {
                    return regulationToken;
                }
            }
        }catch (IllegalAccessException|InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private RegulationToken inovkeMethod(Method method) throws IllegalAccessException, InvocationTargetException{
        RegulationToken returnToken = null;
        TokenRegulation tokenRegulation = method.getAnnotation(TokenRegulation.class);
        if (tokenRegulation != null) {
            returnToken =  setToken(method.invoke(regularObject), tokenRegulation);
        }
        if(returnToken != null) {
            return returnToken;
        }
        IterableToken iterableToken = method.getAnnotation(IterableToken.class);
        if(iterableToken != null ) {
            returnToken = getIteratorNextToken(method.invoke(regularObject), iterableToken);
        }
        return returnToken;
    }

    private RegulationToken setToken(Object tokenValue, TokenRegulation tokenRegulation) {
        if(tokenRegulation.subSession()) {
           Session session = new ObjectSession(tokenValue, stack, tokenRegulation.tokenName());
           stack.push(session);
           RegulationToken returnToken = session.getNextToken();
           if(returnToken == null ) {
               stack.pop();
           }
           return returnToken;
        }
        if(tokenValue == null) {
            return new NullToken();
        }
        return new RegulationToken(tokenRegulation.tokenName() ,tokenValue.toString() ,false);
    }

    private RegulationToken getIteratorNextToken(Object iterable, IterableToken iterableToken) {
        if(iterable == null){
            throw new RuntimeException(" iterable is null");
        }
        if(!(iterable instanceof Iterable)) {
            throw new RuntimeException("iterable is not a instance of Iterable object, return null");
        }
        Session session = new IterableSession((Iterable)iterable, iterableToken, stack);
        stack.push(session);
        RegulationToken returnToken = session.getNextToken();
        if(returnToken == null ) {
            stack.pop();
        }
        return returnToken;
    }
}
