/**
 * 
 */
package br.com.sixinf.ferramentas.log;

import org.apache.log4j.Logger;

/**
 * @author maicon
 *
 */
public class LoggerException extends Exception {

	/**
	 * 
	 */
	public LoggerException(Logger logger) {
		logger.error(fillInStackTrace());
	}

	/**
	 * @param message
	 */
	public LoggerException(String message, Logger logger) {
		super(message);
		logger.error(message, fillInStackTrace());		
	}

	/**
	 * @param cause
	 */
	public LoggerException(Throwable cause, Logger logger) {
		super(cause);
		logger.error(cause.getMessage(), fillInStackTrace());
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LoggerException(String message, Throwable cause, Logger logger) {
		super(message, cause);
		logger.error(message, fillInStackTrace());
	}

}
