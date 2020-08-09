package br.com.di.desafiointer.dominio.digitoUnico;

public class ValorNaoNumericoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4297286820487053777L;

	public ValorNaoNumericoException() {
	}

	public ValorNaoNumericoException(String message) {
		super(message);
	}

	public ValorNaoNumericoException(Throwable cause) {
		super(cause);
	}

	public ValorNaoNumericoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValorNaoNumericoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
