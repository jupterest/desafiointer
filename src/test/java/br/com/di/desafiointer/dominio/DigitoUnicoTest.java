package br.com.di.desafiointer.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import br.com.di.desafiointer.dominio.digitoUnico.DigitoUnico;
import br.com.di.desafiointer.dominio.digitoUnico.LIMITE;
import br.com.di.desafiointer.dominio.digitoUnico.NumeroForaDoLimiteException;
import br.com.di.desafiointer.dominio.digitoUnico.ValorNaoNumericoException;
import br.com.di.desafiointer.infra.ResourceBundleWrapper;

@Tag ("TestesDigitoUnico")
public class DigitoUnicoTest {

	private DigitoUnico digitoUnico;
	private int digito;
	
	@BeforeEach
	public void init() {
		digitoUnico = new DigitoUnico();
		digito = 0;
	}

	@Test
	@DisplayName("calcular quando enviado apenas o valor com 1 digito")
	public void calcularApenasOValor1Digito() {


		try {
			digito = digitoUnico.calcular("2");
			assertEquals(2, digito);
			digito = digitoUnico.calcular("3",null);
			assertEquals(3, digito);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		
	}

	@Test
	@DisplayName("calcular quando enviado apenas o valor com 2 digitos")
	public void calcularApenasOValor2Digitos() {

		try {
			digito = digitoUnico.calcular("29");
			assertEquals(2, digito);
			digito = digitoUnico.calcular("68");
			assertEquals(5, digito);
		} catch (Exception e) {
			fail(e.getMessage());
		}


	}

	@Test
	@DisplayName("calcular quando enviado apenas o valor com 4 digitos")
	public void calcularApenasOValor4Digitos() {

		try {
			digito = digitoUnico.calcular("9875");
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(2, digito);

	}

	



	@Test
	@DisplayName("calcular quando enviado o valor e quatidade de repeticoes")
	public void calcularValorVezes() {

		try {
			digito = digitoUnico.calcular("9875", 4);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(8, digito);
	}
	
	@Test
	@DisplayName("calcular quando enviado o valor com 5 digitos e quatidade de repeticoes")
	public void calcularValorVezesCom5Digitos() {

		try {
			digito = digitoUnico.calcular("53697", 5);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(6, digito);
	}
	
	
	@Test
	@DisplayName("calcular quando enviado o valor com 8 digitos e 6 repeticoes")
	public void calcularValorVezesCom8Digitos6Repeticoes() {

		try {
			digito = digitoUnico.calcular("95874236", 6);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(3, digito);
	}
	
	
	@Test
	@DisplayName("calcular quando enviado o valor com 20 digitos e 6 repeticoes")
	public void lancarExcecaoAoCalcularValorVezesCom20Digitos6Repeticoes() {

		String valor = "95874236979587423697";
		String msgErro = ResourceBundleWrapper.getMessage("msg.erro.valor.nao.numerico", valor);


		ValorNaoNumericoException ex = assertThrows(
				ValorNaoNumericoException.class, 
				() -> digitoUnico.calcular(valor), msgErro
				);

		assertEquals(msgErro, ex.getMessage());
		
	}
	
	
	
	@Test
	@DisplayName("Número invalido: lancar excecao")
	public void lancarExcecaoAoCalcularNumeroInvalido() {

		String valor = "1a";
		String msgErro = ResourceBundleWrapper.getMessage("msg.erro.valor.nao.numerico", valor);


		ValorNaoNumericoException ex = assertThrows(
				ValorNaoNumericoException.class, 
				() -> digitoUnico.calcular(valor), msgErro
				);


		assertEquals(msgErro, ex.getMessage());

	}

	@Test
	@DisplayName("Número abaixo do limite: 0 - lancar excecao NumeroForaDoLimiteException")
	public void lancarExcecaoAoCalcularZeroForaDoLimite() {

		String valor = "0";
		String msgErro = ResourceBundleWrapper.getMessage("msg.erro.numero.abaixo.limite", valor, LIMITE.MINIMO_VALOR.value());


		NumeroForaDoLimiteException ex = assertThrows(
				NumeroForaDoLimiteException.class, 
				() -> digitoUnico.calcular(valor), msgErro
				);


		assertEquals(msgErro, ex.getMessage());

	}
	
	
	@Test
	@DisplayName("Número abaixo do limite: -1 - lancar excecao NumeroForaDoLimiteException")
	public void lancarExcecaoAoCalcularNegativoForaDoLimite() {

		String valor = "-1";
		String msgErro = ResourceBundleWrapper.getMessage("msg.erro.numero.abaixo.limite", valor, LIMITE.MINIMO_VALOR.value());


		NumeroForaDoLimiteException ex = assertThrows(
				NumeroForaDoLimiteException.class, 
				() -> digitoUnico.calcular(valor), msgErro
				);

		assertEquals(msgErro, ex.getMessage());
	}
	
	@Test
	@DisplayName("Número veses abaixo do limite 0 - lancar excecao NumeroForaDoLimiteException")
	public void lancarExcecaoAoCalcularVezesForaDoLimiteVezesZero() {

		String valor = "9875";
		int vezes = 0;
		String msgErro = ResourceBundleWrapper.getMessage("msg.erro.numero.abaixo.limite", vezes, LIMITE.MINIMO_VEZES.value());


		NumeroForaDoLimiteException ex = assertThrows(
				NumeroForaDoLimiteException.class, 
				() -> digitoUnico.calcular(valor, vezes), msgErro
				);


		assertEquals(msgErro, ex.getMessage());
	}
	
	
	@Test
	@DisplayName("Número acima do limite 10^5 + 1 - lancar excecao NumeroForaDoLimiteException")
	public void lancarExcecaoAoCalcularVezesForaDoLimiteVezesMaior() {

		String valor = "9875";
		int vezesForaDoLimite = LIMITE.MAXIMO_VEZES.value() + 1;
		
		String msgErro = ResourceBundleWrapper.getMessage("msg.erro.numero.acima.limite", vezesForaDoLimite, LIMITE.MAXIMO_VEZES.value());


		NumeroForaDoLimiteException ex = assertThrows(
				NumeroForaDoLimiteException.class, 
				() -> digitoUnico.calcular(valor, vezesForaDoLimite), msgErro
				);


		assertEquals(msgErro, ex.getMessage());

	}

}
