package imgb.rulesystem.node.exception;

public class InvalidJsonNodeKeyException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidJsonNodeKeyException(String str) {
		super(str);
	}

	public InvalidJsonNodeKeyException() {
		super();
	}
}
