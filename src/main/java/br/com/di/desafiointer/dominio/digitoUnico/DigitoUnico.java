package br.com.di.desafiointer.dominio.digitoUnico;

import java.util.Arrays;
import java.util.List;

import br.com.di.desafiointer.infra.ResourceBundleWrapper;

public class DigitoUnico {

	private String valor;
	private Integer digito;
	
	private Integer digitoCalculado;
	
	
	
	public int calcular(String valor, int... arrayNumeroDeVezes) throws ValorNaoNumericoException, NumeroForaDoLimiteException{
		this.valor = valor;

		int numeroDeVezes = arrayNumeroDeVezes.length>0 ? arrayNumeroDeVezes[0] : 1;
		verificarLimiteDeVezes(numeroDeVezes);
		
		digito = calcular(valor) * numeroDeVezes;
		
		while(digito > 9) digito = calcular(digito.toString());
		
		return digito;
		
	}

	private int calcular(String valor) throws ValorNaoNumericoException, NumeroForaDoLimiteException{

		verificarLimiteDoNumero(valor);

		digitoCalculado = 0;

		List<String> listValor = Arrays.asList(valor.split(""));

		listValor.stream()
		.map( e -> Integer.parseInt(e) )
		.forEach( d -> digitoCalculado +=  d );

		return digitoCalculado < 10 ? digitoCalculado : calcular(digitoCalculado.toString());
	}


	private void verificarLimiteDoNumero(String valor) throws NumeroForaDoLimiteException, ValorNaoNumericoException{
		int valorNumerico = converterEmInteiro(valor);
		// 1 <= n <= 10ˆ1000000
		if (valorNumerico < 1) {
			throw new NumeroForaDoLimiteException(ResourceBundleWrapper.getMessage("msg.erro.numero.abaixo.limite", valor, LIMITE.MINIMO_VALOR.value()));
		}
	}
	
	private void verificarLimiteDeVezes(int valor) throws NumeroForaDoLimiteException{
		// 1 <= k <= 10ˆ5
		if (valor < 1) {
			throw new NumeroForaDoLimiteException(ResourceBundleWrapper.getMessage("msg.erro.numero.abaixo.limite", valor, LIMITE.MINIMO_VEZES.value()));
		}
		
		if (valor > LIMITE.MAXIMO_VEZES.value()) {
			throw new NumeroForaDoLimiteException(ResourceBundleWrapper.getMessage("msg.erro.numero.acima.limite", valor, LIMITE.MAXIMO_VEZES.value()));
		}
		
	}
	
	private int converterEmInteiro(String valor) throws ValorNaoNumericoException{
		int valorNumerico = 0;

		try {
			valorNumerico = Integer.parseInt(valor);
		} catch (NumberFormatException e) {
			throw new ValorNaoNumericoException(ResourceBundleWrapper.getMessage("msg.erro.valor.nao.numerico", valor));
		}

		return valorNumerico;
	}

	public String getValor() {
		return valor;
	}

	public Integer getDigito() {
		return digito;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setDigito(Integer digito) {
		this.digito = digito;
	}

}
