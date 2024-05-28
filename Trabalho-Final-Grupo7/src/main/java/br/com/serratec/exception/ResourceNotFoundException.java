package br.com.serratec.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1647232871390212612L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
