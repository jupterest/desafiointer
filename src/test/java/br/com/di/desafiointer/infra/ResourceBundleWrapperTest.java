package br.com.di.desafiointer.infra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag ("TestesDosTextosDasMensagens")
public class ResourceBundleWrapperTest {
	
	@Test
	@DisplayName("retornar mensagem de email inválido")
	public void msgErroEmailInvalido() {
		String msgEsperada = "Endereço de e-mail inválido (123).";
		String msgRetornada = ResourceBundleWrapper.getMessage("msg.erro.email.invalido", "123");
		assertEquals(msgEsperada, msgRetornada);
	}
	
	@Test
	@DisplayName("retornar mensagem erro abaixo do limite")
	public void msgErroAbaixoLimite() {
		String msgEsperada = "O número 123 está abaixo do limite 1.";
		String msgRetornada = ResourceBundleWrapper.getMessage("msg.erro.numero.abaixo.limite", "123", "1");
		assertEquals(msgEsperada, msgRetornada);
	}
	
	
	@Test
	@DisplayName("retornar mensagem erro acima do limite")
	public void msgErroAcimaLimite() {
		String msgEsperada = "O número 123 está acima do limite 1.";
		String msgRetornada = ResourceBundleWrapper.getMessage("msg.erro.numero.acima.limite", "123", "1");
		assertEquals(msgEsperada, msgRetornada);
	}
	
	
	@Test
	@DisplayName("retornar mensagem de erro valor nao numerico")
	public void msgErroValorNaoNumerico() {
		String msgEsperada = "O valor a não é um número válido.";
		String msgRetornada = ResourceBundleWrapper.getMessage("msg.erro.valor.nao.numerico", "a");
		assertEquals(msgEsperada, msgRetornada);
	}

	@Test
	@DisplayName("retornar mensagem de erro obrigatório o preenchimento do objeto")
	public void msgObrigatorioPreenchimentoObjeto() {
		String msgEsperada = "É obrigatório o preenchimento do objeto a.";
		String msgRetornada = ResourceBundleWrapper.getMessage("msg.erro.objeto.obrigatorio", "a");
		assertEquals(msgEsperada, msgRetornada);
	}
	
	
}
