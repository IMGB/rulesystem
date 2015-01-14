package imgb.rulesystem.rulereader.token;

/**
 * Created by ca on 8/4/14.<br>
 */
public abstract class RuleToken {

    public static final RuleToken END_TOKEN = new EndToken();
    public static final RuleToken NULL_TOKEN = new NullToken();

    public abstract String getTokenName();

    public abstract String getTokenString();

    public abstract Double getTokenDouble();

    public abstract Integer getTokenInt();

    public abstract boolean beSessionEnd();

    public String toString() {
        return "tokenname:" + this.getTokenName() +
                " token value:" + this.getTokenString() + " beSession:" + this.beSessionEnd();
    }
}
