package br.com.di.desafiointer.dominio;

public class RegistroNaoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4228511812751708706L;

	public RegistroNaoEncontradoException() {
	}

	public RegistroNaoEncontradoException(String message) {
		super(message);
	}

	public RegistroNaoEncontradoException(Throwable cause) {
		super(cause);
	}

	public RegistroNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegistroNaoEncontradoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
