package imgb.rulesystem.node.information.nodeinfo;

/**
 * Created by Daniel on 2014/7/26.
 */
public class RuleInfo extends NodeTreeInfo {

    private static final String RULE_DESCRIPATION_KEY = "ruleDescription";
    private static final String RULE_ID_KEY = "ruleId";

    public RuleInfo(String ruleId) {
        infoContainer.put("ruleId", ruleId);
    }

    public RuleInfo(String ruleId, String ruleDescription) {
        super.setInfo(RULE_DESCRIPATION_KEY, ruleDescription);
        super.setInfo(RULE_ID_KEY, ruleId);
    }

    public String getRuleId() {
        return (String) (super.getInfo(RULE_ID_KEY));
    }

    public String getRuleDescripation() {
        return (String) (super.getInfo(RULE_DESCRIPATION_KEY));
    }

}
