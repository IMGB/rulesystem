package imgb.rulesystem.rulepersister.exception;

/**
 * Created by is on 1/14/15.
 */
public class PersistException extends Exception {
    public PersistException(String message) {
        super(message);
    }

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }
}
