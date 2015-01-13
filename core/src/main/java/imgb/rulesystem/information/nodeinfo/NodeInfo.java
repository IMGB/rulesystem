package imgb.rulesystem.information.nodeinfo;


import imgb.rulesystem.information.Information;

/**
 * Created by ca on 8/13/14.<br>
 */
public class NodeInfo extends Information {
    private static final String TOKEN_NAME = "tokenName";
    private static final String TOKEN_VALUE = "tokenValue";


    public String getTokenName() {
        Object name = getInfo(TOKEN_NAME);
        return (String) name;
    }

    public Object getTokenValue() {
        return getInfo(TOKEN_VALUE);
    }

    public void setToken(String tokenName, Object tokenValue) {
        setInfo(TOKEN_NAME, tokenName);
        setInfo(TOKEN_VALUE, tokenValue);
    }
}
