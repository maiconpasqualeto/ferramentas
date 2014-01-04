/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sixinf.ferramentas.persistencia;

import org.apache.log4j.Logger;

import br.com.sixinf.ferramentas.log.LoggerException;

/**
 *
 * @author maicon.pasqualeto
 */
public class PersistenciaException extends LoggerException {

    /**
	 *
	 */
	public PersistenciaException(Logger logger) {
            super(logger);
	}

	/**
	 * @param message
	 */
	public PersistenciaException(String message, Logger logger) {
            super(message, logger);
	}

	/**
	 * @param cause
	 */
	public PersistenciaException(Throwable cause, Logger logger) {
		super(cause, logger);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PersistenciaException(String message, Throwable cause, Logger logger) {
		super(message, cause, logger);
	}
}
