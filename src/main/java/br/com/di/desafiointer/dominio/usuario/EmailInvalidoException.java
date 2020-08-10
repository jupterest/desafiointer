package br.com.di.desafiointer.dominio.usuario;

public class EmailInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6368252827476228827L;

	public EmailInvalidoException() {
	}

	public EmailInvalidoException(String message) {
		super(message);
	}

	public EmailInvalidoException(Throwable cause) {
		super(cause);
	}

	public EmailInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailInvalidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
