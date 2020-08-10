package br.com.di.desafiointer.aplicacao.digitoUnico;

public class DigitoUnicoDTO {

	private Integer id;
	private String valor;
	private Integer digito;
	private Integer numeroDeVezes;
	
	public DigitoUnicoDTO() {
		
	}
	
	public DigitoUnicoDTO(String valor, Integer numeroDeVezes) {
		this.valor = valor;
		this.numeroDeVezes = numeroDeVezes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
