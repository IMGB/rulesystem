package imgb.rulesystem.rulereader.token;

/**
 * Created by is on 1/14/15.
 */
public class EndToken extends RuleToken{

    @Override
    public String getTokenName() {
        return "";
    }

    @Override
    public String getTokenString() {
        return "session end";
    }

    @Override
    public Double getTokenDouble() {
        return null;
    }

    @Override
    public Integer getTokenInt() {
        return null;
    }

    @Override
    public boolean beSessionEnd() {
        return true;
    }
}
