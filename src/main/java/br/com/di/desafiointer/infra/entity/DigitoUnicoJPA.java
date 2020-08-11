package br.com.di.desafiointer.infra.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.di.desafiointer.dominio.digitoUnico.DigitoUnico;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;

@Entity
public class DigitoUnicoJPA {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String valor;
	
	private Integer digito;
	
	private Integer numeroDeVezes;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn()
	private UsuarioJPA usuario;
	
	public DigitoUnicoJPA() {
	}
	
	public DigitoUnicoJPA(DigitoUnico digitoUnico) {
		this.id = digitoUnico.getId();
		this.digito = digitoUnico.getDigito();
		this.numeroDeVezes = digitoUnico.getNumeroDeVezes();
		this.valor = digitoUnico.getValor();
		this.usuario = new UsuarioJPA(digitoUnico.getUsuario());
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

	public DigitoUnico converter() {
		DigitoUnico digito = null;
		
		try {
			digito = new DigitoUnico(this.id, this.valor, this.digito, this.numeroDeVezes);
			if(this.usuario!=null) {
				digito.setUsuario(this.usuario.converter());
			}			
			return digito;
		} catch (EmailInvalidoException e) {
			return digito;
		}
	}
	

}
