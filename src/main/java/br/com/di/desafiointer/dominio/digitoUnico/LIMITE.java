package br.com.di.desafiointer.dominio.digitoUnico;

public enum LIMITE {

	MAXIMO_VEZES ( (int)Math.pow(10, 5) ),
	MINIMO_VEZES (1),
	MINIMO_VALOR (1); 
	
	private final int valorLimite;
	
	LIMITE(int valorLimite) {
		this.valorLimite = valorLimite;
	}
	
	public int value() {
		return valorLimite;
	}
}
