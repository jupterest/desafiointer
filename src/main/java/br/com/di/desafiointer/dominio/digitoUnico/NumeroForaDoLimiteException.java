package br.com.di.desafiointer.dominio.digitoUnico;

public class NumeroForaDoLimiteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2076069874568031000L;

	public NumeroForaDoLimiteException() {
	}

	public NumeroForaDoLimiteException(String message) {
		super(message);
	}

	public NumeroForaDoLimiteException(Throwable cause) {
		super(cause);
	}

	public NumeroForaDoLimiteException(String message, Throwable cause) {
		super(message, cause);
	}

	public NumeroForaDoLimiteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
