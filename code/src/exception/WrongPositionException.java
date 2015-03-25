package exception;

import structure.Node;

public class WrongPositionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node node;

	public WrongPositionException(Node node) {
		super();
		this.node = node;
	}

	@Override
	public String getMessage() {
		return "Wrong position at " + node.getKey();
	}
}
