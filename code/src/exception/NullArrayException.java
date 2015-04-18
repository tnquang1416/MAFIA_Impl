package exception;

public class NullArrayException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NullArrayException(){
		super("The input array has no value");
	}
}
