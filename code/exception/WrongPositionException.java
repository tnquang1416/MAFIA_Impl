package exception;

import structure.NodeV3;

public class WrongPositionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NodeV3 node;

	public WrongPositionException(NodeV3 node) {
		super();
		this.node = node;
	}

	@Override
	public String getMessage() {
		return "Wrong position at " + node.toString();
	}
}
