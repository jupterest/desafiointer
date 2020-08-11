package br.com.di.desafiointer.dominio.digitoUnico;

import java.util.Arrays;
import java.util.List;

import br.com.di.desafiointer.dominio.usuario.Usuario;
import br.com.di.desafiointer.infra.ResourceBundleWrapper;

public class DigitoUnico {

	private Integer id;
	private String valor;
	private Integer digito;
	private Integer numeroDeVezes;
	private Usuario usuario;

	private Integer digitoCalculado;

	public DigitoUnico() {
	}
	
	public DigitoUnico(Integer id, String valor, Integer digito, Integer numeroDeVezes) {
		this.id = id;
		this.valor = valor;
		this.digito = digito;
		this.numeroDeVezes = numeroDeVezes;
	}

	public int calcular(String valor, int... arrayNumeroDeVezes) throws ValorNaoNumericoException, NumeroForaDoLimiteException{
		this.valor = valor;

		numeroDeVezes = arrayNumeroDeVezes!=null && arrayNumeroDeVezes.length>0 ? arrayNumeroDeVezes[0] : 1;
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

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getDigito() {
		return digito;
	}

	public void setDigito(Integer digito) {
		this.digito = digito;
	}

	public Integer getNumeroDeVezes() {
		return numeroDeVezes;
	}

	public void setNumeroDeVezes(Integer numeroDeVezes) {
		this.numeroDeVezes = numeroDeVezes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



}
