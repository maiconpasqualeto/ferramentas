/**
 * 
 */
package br.com.sixinf.ferramentas.facade;

import org.apache.log4j.Logger;

import br.com.sixinf.ferramentas.log.LoggerException;

/**
 * @author maicon
 *
 */
public class FacadeException extends LoggerException {

	/**
	 * 
	 */
	public FacadeException(Logger logger) {
		super(logger);		
	}

	/**
	 * @param message
	 */
	public FacadeException(String message, Logger logger) {
		super(message, logger);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FacadeException(String message, Throwable cause, Logger logger) {
		super(message, cause, logger);
	}

	/**
	 * @param cause
	 */
	public FacadeException(Throwable cause, Logger logger) {
		super(cause, logger);
	}

}
