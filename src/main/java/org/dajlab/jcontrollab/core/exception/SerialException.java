package org.dajlab.jcontrollab.core.exception;

import org.dajlab.core.DaJLabRuntimeException;

/**
 * Serial port exception.
 * 
 * @author Erik Amzallag
 * 
 */
public class SerialException extends DaJLabRuntimeException {

	/**
	 * Constructor.
	 * 
	 */
	public SerialException() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            a message.
	 */
	public SerialException(String message) {
		super(message);
	}

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -7429359497773552069L;

}
