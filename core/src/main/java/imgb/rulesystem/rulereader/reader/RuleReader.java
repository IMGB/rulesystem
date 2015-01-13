package imgb.rulesystem.rulereader.reader;

import imgb.rulesystem.rulereader.token.RuleToken;

/**
 * Created by ca on 8/4/14.<br>
 *     RuleReader的基类
 */
public abstract class RuleReader {
    /**
     * 获得当前的rule reader 是否有下一个token
     * @return
     */
    public abstract boolean hasNext();

    /**
     * 获得下一个rule token
     * @return
     */
    public abstract RuleToken getNextToken();

    /**
     * 获得当前的规则类型
     * @return
     */
    public abstract String getRuleType();
}