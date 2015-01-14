package imgb.rulesystem.rulereader;

import imgb.rulesystem.rulereader.reader.RuleReader;
import imgb.rulesystem.rulereader.token.RuleToken;

/**
 * Created by is on 1/14/15.
 */
public class XmlRuleReader extends RuleReader{
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public RuleToken getNextToken() {
        return null;
    }

    @Override
    public String getRuleType() {
        return null;
    }
}
