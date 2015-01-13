package imgb.rulesystem.rulereader.regulation.parser.session;

import imgb.rulesystem.rulereader.token.RegulationToken;

import java.util.Stack;

/**
 * Created by Daniel on 2014/10/10.
 * 此session 代表将一个承载规则信息的对象分割为一个个session 将此session 可变成token流
 */
public abstract class Session {
    protected RegulationToken endSession = null;
    protected RegulationToken beginSession = null;
    protected Stack<Session> stack;

    /**
     * 获得除去首尾之外可用的token
     * @return
     */
    public abstract RegulationToken getNextAvailableToken();

    public Session(Stack<Session> stack) {
        super();
        this.stack = stack;
    }

    /**
     *
     * @return 将获得当前session，或者子session 中的第一个出现可用的 RegulationToken
     * 如果当前的session没有正常的ruletoken时 返回null.
     */
    public RegulationToken getNextToken() {
        RegulationToken token = null;
        if(beginSession != null) {
            token = beginSession;
            beginSession = null;
            return token;
        }

        token = getNextAvailableToken();
        if(token != null) {
            return token;
        }
        if(endSession != null) {
            token = endSession;
            endSession = null;
            return token;
        }
        return token;
    }
}
