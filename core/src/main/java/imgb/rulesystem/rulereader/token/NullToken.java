package imgb.rulesystem.rulereader.token;

/**
 * Created by ca on 10/15/14.<br>
 */
public class NullToken extends RegulationToken {

    public NullToken() {
        super(null,null,false);
    }

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
    public Object getTokenObject() {
        return null;
    }

    @Override
    public boolean beSessionEnd() {
        return super.beSessionEnd();
    }
}
