package br.com.di.desafiointer.dominio.digitoUnico;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.di.desafiointer.infra.ResourceBundleWrapper;

public class DigitoUnico {

	private Integer digitoCalculado;

	public int calcular(String valor) throws ValorNaoNumericoException, NumeroForaDoLimiteException{

		int valorNumerico = converterEmInteiro(valor);
		verificarLimiteDoNumero(valorNumerico);

		digitoCalculado = 0;

		List<String> listValor = Arrays.asList(valor.split(""));

		listValor.stream()
		.map( e -> Integer.parseInt(e) )
		.forEach( d -> digitoCalculado +=  d );

		return digitoCalculado < 10 ? digitoCalculado : calcular(digitoCalculado.toString());
	}



	public int calcularVezesComRestricao(String valor, int numeroDeVezes) throws ValorNaoNumericoException, NumeroForaDoLimiteException{

		List<String> listValor = Collections.nCopies(numeroDeVezes, valor);
		StringBuilder valorVezes = new StringBuilder();

		listValor.stream()
		.forEach( d -> valorVezes.append(d) );

		String p = valorVezes.toString();

		return calcular(p);
	}

	public int calcular(String valor, int numeroDeVezes) throws ValorNaoNumericoException, NumeroForaDoLimiteException{

		Integer digito = calcular(valor) * numeroDeVezes;
		
		while(digito > 9) digito = calcular(digito.toString());
		
		return digito;
		
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


	private void verificarLimiteDoNumero(int valor) throws NumeroForaDoLimiteException{
		// 1 <= n <= 10ˆ1000000
		if (valor < 1) {
			throw new NumeroForaDoLimiteException(ResourceBundleWrapper.getMessage("msg.erro.numero.fora.limite", valor));
		}
	}

}
