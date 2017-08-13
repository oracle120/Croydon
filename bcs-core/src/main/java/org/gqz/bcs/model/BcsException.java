package org.gqz.bcs.model;

/**
 * @author jinhuer168
 *
 */
public class BcsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public BcsException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BcsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BcsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public BcsException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BcsException(Throwable cause) {
		super(cause);
	}

}
