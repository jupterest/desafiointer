package br.com.di.desafiointer.infra.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.di.desafiointer.dominio.digitoUnico.DigitoUnico;
import br.com.di.desafiointer.dominio.usuario.Email;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;
import br.com.di.desafiointer.dominio.usuario.Usuario;

@Entity
public class UsuarioJPA {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String email;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER,
	            cascade = CascadeType.ALL)
	private List<DigitoUnicoJPA> resultados;
	
	public UsuarioJPA() {
	}
	
	public UsuarioJPA(Usuario usuario) {
		
		this.email = usuario.getEmail().getEndereco();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		
		if(usuario.getResultados()!=null)
			this.resultados = usuario.getResultados().stream()
				.map(DigitoUnicoJPA::new)
				.collect(Collectors.toList());
	}
	
	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public List<DigitoUnicoJPA> getResultados() {
		return resultados;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setResultados(List<DigitoUnicoJPA> resultados) {
		this.resultados = resultados;
	}
	
	public Usuario converter() throws EmailInvalidoException {
		return new Usuario(this.id, this.nome, new Email(this.email));
	}

	public Usuario toUsuario() throws EmailInvalidoException {
		List<DigitoUnico> resultados =  new ArrayList<DigitoUnico>();

		if(this.resultados!=null) {
			this.resultados.forEach( d -> resultados.add( d.converter() ));
		}

		return new Usuario(this.id, this.nome, new Email(this.email), resultados);
	}
	
	public static List<Usuario> converter(List<UsuarioJPA> usuarios){
		return usuarios.stream()
				.map(t -> {
					try {
						return t.toUsuario();
					} catch (EmailInvalidoException e) {
						return new Usuario(t.id, t.nome);
					}
				})
				.collect(Collectors.toList());
		
	}

}
