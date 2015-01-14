package imgb.rulesystem.rulereader.token;

/**
 * Created by is on 1/14/15.
 */
public class EndToken extends RuleToken{

    @Override
    public String getTokenName() {
        return null;
    }

    @Override
    public String getTokenString() {
        return null;
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
