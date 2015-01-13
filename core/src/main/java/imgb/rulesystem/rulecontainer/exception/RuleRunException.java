package imgb.rulesystem.rulecontainer.exception;

/**
 * Created by is on 1/13/15.
 */
public class RuleRunException extends RuleException {
    public RuleRunException(String message) {
        super(message);
    }

    public RuleRunException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleRunException(Throwable cause) {
        super(cause);
    }
}
