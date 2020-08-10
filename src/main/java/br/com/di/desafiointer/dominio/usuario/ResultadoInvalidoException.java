package br.com.di.desafiointer.dominio.usuario;

public class ResultadoInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 357578282130069575L;

	public ResultadoInvalidoException() {
	}

	public ResultadoInvalidoException(String message) {
		super(message);
	}

	public ResultadoInvalidoException(Throwable cause) {
		super(cause);
	}

	public ResultadoInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResultadoInvalidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
