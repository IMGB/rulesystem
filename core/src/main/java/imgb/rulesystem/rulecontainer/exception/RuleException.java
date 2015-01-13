package imgb.rulesystem.rulecontainer.exception;

/**
 * Created by is on 1/13/15.
 */
public class RuleException extends Exception{
    public RuleException(String message) {
        super(message);
    }

    public RuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleException(Throwable cause) {
        super(cause);
    }
}
