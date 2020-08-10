package br.com.di.desafiointer.infra.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DigitoUnicoJPA {

	@Id
	private Integer id;
	
	private String valor;
	
	private Integer digito;
	
	private Integer numeroDeVezes;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
	private UsuarioJPA usuario;
	
	public DigitoUnicoJPA() {
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

	public UsuarioJPA getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioJPA usuario) {
		this.usuario = usuario;
	}

	

}
