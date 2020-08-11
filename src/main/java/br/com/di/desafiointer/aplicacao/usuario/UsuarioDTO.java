package br.com.di.desafiointer.aplicacao.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.di.desafiointer.aplicacao.digitoUnico.DigitoUnicoDTO;
import br.com.di.desafiointer.dominio.usuario.Email;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;
import br.com.di.desafiointer.dominio.usuario.Usuario;


public class UsuarioDTO {
	
	private Integer id;
	
	@NotNull 
	@Size(min=5, max=250)
	private String nome;
	
	@NotNull 
	@javax.validation.constraints.Email(message = "Informe um e-mail válido.")
	@Size(min=5, max=300)
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
		
		List<DigitoUnicoDTO> resultados =  new ArrayList<DigitoUnicoDTO>();

		if(usuario.getResultados()!=null) {
			usuario.getResultados().forEach( d -> resultados.add( new DigitoUnicoDTO(d) ));
		}
		
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail().getEndereco();
		this.resultados = resultados;
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
	
	public Usuario converter() throws EmailInvalidoException {
		return new Usuario(this.id, this.nome, new Email(this.email));
	}
	
	public static List<UsuarioDTO> converter(List<Usuario> usuarios) {
		return usuarios.stream()
				.map(UsuarioDTO::new)
				.collect(Collectors.toList());
	}

}
