package br.com.di.desafiointer.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import br.com.di.desafiointer.dominio.digitoUnico.DigitoUnico;
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
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(2, digito);
	}

	@Test
	@DisplayName("calcular quando enviado apenas o valor com 2 digitos")
	public void calcularApenasOValor2Digitos() {

		try {
			digito = digitoUnico.calcular("29");
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertEquals(2, digito);

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
	@DisplayName("Número invalido: lancar excecao")
	public void lancarExcecaoAoCalcularNumeroInvalido() {

		String valor = "1a";
		String msgErro = ResourceBundleWrapper.getMessage("msg.erro.valor.nao.numerico", valor);


		ValorNaoNumericoException ex = assertThrows(
				ValorNaoNumericoException.class, 
				() -> digitoUnico.calcular(valor), msgErro
				);


		assertEquals(ex.getMessage(), msgErro);

	}

	@Test
	@DisplayName("Número fora do limite: 0 - lancar excecao NumeroForaDoLimiteException")
	public void lancarExcecaoAoCalcularZeroForaDoLimite() {

		String valor = "0";
		String msgErro = ResourceBundleWrapper.getMessage("msg.erro.numero.fora.limite", valor);


		NumeroForaDoLimiteException ex = assertThrows(
				NumeroForaDoLimiteException.class, 
				() -> digitoUnico.calcular(valor), msgErro
				);


		assertEquals(ex.getMessage(), msgErro);

	}
	
	
	@Test
	@DisplayName("Número fora do limite: -1 - lancar excecao NumeroForaDoLimiteException")
	public void lancarExcecaoAoCalcularNegativoForaDoLimite() {

		String valor = "-1";
		String msgErro = ResourceBundleWrapper.getMessage("msg.erro.numero.fora.limite", valor);


		NumeroForaDoLimiteException ex = assertThrows(
				NumeroForaDoLimiteException.class, 
				() -> digitoUnico.calcular(valor), msgErro
				);


		assertEquals(ex.getMessage(), msgErro);

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

}
