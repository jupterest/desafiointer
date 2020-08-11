package br.com.di.desafiointer.infra.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.di.desafiointer.dominio.usuario.Email;
import br.com.di.desafiointer.dominio.usuario.EmailInvalidoException;
import br.com.di.desafiointer.dominio.usuario.Usuario;

@Entity
public class UsuarioJPA {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String email;
	
	 @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	private List<DigitoUnicoJPA> resultados;
	
	public UsuarioJPA() {
	}
	
	public UsuarioJPA(Usuario usuario) {
		this.email = usuario.getEmail().getEndereco();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
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

	public Usuario toUsuario() throws EmailInvalidoException {
		return new Usuario(this.id, this.nome, new Email(this.email));
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
