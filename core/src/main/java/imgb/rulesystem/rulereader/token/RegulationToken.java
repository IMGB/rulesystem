package imgb.rulesystem.rulereader.token;

/**
 * Created by Gaoshuai on 2014/9/3.
 */
public class RegulationToken extends RuleToken {
    private String key;
    private String value;
    boolean isSessionEnd;


    /**
     * @param key
     * @param value
     * @param isSessionEnd
     */
    public RegulationToken(String key, String value, boolean isSessionEnd) {
        this.key = key;
        this.value = value;
        this.isSessionEnd = isSessionEnd;
    }

    /**
     * @return
     */
    public String getTokenName() {
        return key;
    }

    /**
     * @return
     */
    public String getTokenString() {
        return value.trim();
    }

    /**
     * @return
     */
    public Double getTokenDouble() {
        Double returnDouble = null;

        try {
            returnDouble = Double.valueOf(value);
        } catch (Exception e) {
            returnDouble = null;
        }
        return returnDouble;
    }

    /**
     * @return
     */
    public Object getTokenObject() {
        return null;
    }

    /**
     * @return
     */
    public Integer getTokenInt() {
        return Integer.valueOf(value);
    }


    /**
     * @return
     */
    public boolean beSessionEnd() {
        return isSessionEnd;
    }

    public void setSessionEnd(Boolean sessionEnd) {
        isSessionEnd = sessionEnd;
    }

}
