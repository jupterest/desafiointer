package br.com.di.desafiointer.aplicacao.usuario;

import java.util.List;

import br.com.di.desafiointer.aplicacao.digitoUnico.DigitoUnicoDTO;
import br.com.di.desafiointer.dominio.usuario.Usuario;

public class UsuarioDTO {
	
	private Integer id;
	private String nome;
	private String email;
	private List<DigitoUnicoDTO> resultados; 

	public UsuarioDTO() {
	}
	
	public UsuarioDTO(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}
	

	public UsuarioDTO(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail().getEndereco();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<DigitoUnicoDTO> getResultados() {
		return resultados;
	}

	public void setResultados(List<DigitoUnicoDTO> resultados) {
		this.resultados = resultados;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
