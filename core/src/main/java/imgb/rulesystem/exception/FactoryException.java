package imgb.rulesystem.exception;

public class FactoryException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -2005096823527078275L;

    public FactoryException() {
        super();
    }

    public FactoryException(String message, Throwable cause,
                            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FactoryException(String message) {
        super(message);
    }

    public FactoryException(Throwable cause) {
        super(cause);
    }


}
