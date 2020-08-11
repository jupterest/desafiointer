package br.com.di.desafiointer.aplicacao.digitoUnico;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.di.desafiointer.aplicacao.usuario.UsuarioDTO;
import br.com.di.desafiointer.dominio.digitoUnico.DigitoUnico;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;

public class DigitoUnicoDTO {

	private Integer id;
	
	@NotNull 
	@Size(min=1)
	private String valor;
	
	private Integer digito;
	
	@Min(value = 1, message = "O valor minimo é 1")
    @Max(value = 2147483647, message = "O valor máximo é 2147483647")
	private Integer numeroDeVezes;
	
	private UsuarioDTO usuario;
	
	public DigitoUnicoDTO() {
		
	}
	
	public DigitoUnicoDTO(String valor, Integer numeroDeVezes) {
		this.valor = valor;
		this.numeroDeVezes = numeroDeVezes;
	}

	public DigitoUnicoDTO(Integer id, @NotNull @Size(min = 1) String valor, Integer digito,
			@Min(value = 1, message = "O valor minimo é 1") @Max(value = 2147483647, message = "O valor máximo é 2147483647") Integer numeroDeVezes,
			UsuarioDTO usuario) {
		this.id = id;
		this.valor = valor;
		this.digito = digito;
		this.numeroDeVezes = numeroDeVezes;
		this.usuario = usuario;
	}

	public DigitoUnicoDTO(DigitoUnico digitoUnico) {
		this.valor = digitoUnico.getValor();
		this.numeroDeVezes = digitoUnico.getNumeroDeVezes();
		this.digito = digitoUnico.getDigito();
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
		return numeroDeVezes == null ? 1 : numeroDeVezes;
	}

	public void setNumeroDeVezes(Integer numeroDeVezes) {
		this.numeroDeVezes = numeroDeVezes;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
